package com.crcl.authentication.service.impl;

import com.crcl.authentication.domain.Permission;
import com.crcl.authentication.dto.PermissionDto;
import com.crcl.authentication.mappers.PermissionMapper;
import com.crcl.authentication.repository.PermissionRepository;
import com.crcl.authentication.service.PermissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Override
    public PermissionDto save(PermissionDto permissionDto) {
        log.debug("Saving permission: {}", permissionDto);
        Permission permission = this.permissionMapper.toEntity(permissionDto);
        Permission savedPermission = permissionRepository.save(permission);
        PermissionDto savedPermissionDto = permissionMapper.toDto(savedPermission);
        log.debug("Saved permission: {}", savedPermissionDto);
        return savedPermissionDto;
    }

    @Override
    public List<PermissionDto> save(List<PermissionDto> entities) {
        log.debug("Saving permissions: {}", entities);
        List<PermissionDto> savedEntities = entities.stream().map(this::save).toList();
        log.debug("Saved permissions: {}", savedEntities);
        return savedEntities;
    }

    @Override
    public void deleteById(String id) {
        permissionRepository.findById(id).ifPresent(permission -> {
            permission.setEnabled(false);
            Permission savedPermission = permissionRepository.save(permission);
            log.debug("Disabled permission with id {}: {}", savedPermission.getId(), savedPermission);
        });
    }

    @Override
    public PermissionDto findById(String id) {
        log.debug("Finding permission by id: {}", id);
        return permissionRepository.findById(id)
                .map(permissionMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<PermissionDto> findAll() {
        log.debug("Finding all permissions");
        List<PermissionDto> permissionDtos = permissionRepository.findAll().stream()
                .map(permissionMapper::toDto)
                .toList();
        log.debug("Found {} permissions", permissionDtos.size());
        return permissionDtos;
    }

    @Override
    public Page<PermissionDto> findAll(Pageable pageable) {
        log.debug("Finding permissions by page: {}", pageable);
        Page<PermissionDto> permissionDtoPage = permissionRepository.findAll(pageable)
                .map(permissionMapper::toDto);
        log.debug("Found {} permissions", permissionDtoPage.getNumberOfElements());
        return permissionDtoPage;
    }

    @Override
    public PermissionDto update(PermissionDto permissionDto, String id) {
        log.debug("Updating permission with id {}: {}", id, permissionDto);
        return permissionRepository.findById(id)
                .map(permission -> permissionMapper.toEntity(permissionDto))
                .map(permissionRepository::save)
                .map(permissionMapper::toDto)
                .orElse(null);
    }

}
