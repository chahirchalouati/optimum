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
        Permission permission = this.permissionMapper.toEntity(permissionDto);
        return permissionMapper.toDto(permissionRepository.save(permission));
    }

    @Override
    public List<PermissionDto> save(List<PermissionDto> entities) {
        return entities.stream().map(this::save).toList();
    }

    @Override
    public void deleteById(String id) {
        permissionRepository.findById(id).ifPresent(permission -> {
            permission.setEnabled(false);
            permissionRepository.save(permission);
            log.info("permission with id %s was disabled".formatted(permission.getId()));
        });

    }

    @Override
    public PermissionDto findById(String id) {
        return permissionRepository.findById(id)
                .map(permissionMapper::toDto)
                .orElse(null);
    }


    @Override
    public List<PermissionDto> findAll() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toDto)
                .toList();
    }

    @Override
    public Page<PermissionDto> findAll(Pageable pageable) {
        return permissionRepository.findAll(pageable)
                .map(permissionMapper::toDto);
    }

    @Override
    public PermissionDto update(PermissionDto permissionDto, String id) {
        return permissionRepository.findById(id)
                .map(permission -> permissionMapper.toEntity(permissionDto))
                .map(permissionRepository::save)
                .map(permissionMapper::toDto)
                .orElse(null);
    }
}
