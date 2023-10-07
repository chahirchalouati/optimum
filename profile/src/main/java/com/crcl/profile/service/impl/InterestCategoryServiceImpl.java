package com.crcl.profile.service.impl;

import com.crcl.profile.domain.InterestCategory;
import com.crcl.profile.dto.InterestCategoryDto;
import com.crcl.profile.mappers.InterestCategoryMapper;
import com.crcl.profile.repository.InterestCategoryRepository;
import com.crcl.profile.service.InterestCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterestCategoryServiceImpl implements InterestCategoryService {
    private final InterestCategoryRepository interestCategoryRepository;
    private final InterestCategoryMapper interestCategoryMapper;

    @Override
    public InterestCategoryDto save(InterestCategoryDto interestCategory) {
        InterestCategory entity = interestCategoryMapper.toEntity(interestCategory);
        InterestCategory savedInterestCategory = interestCategoryRepository.save(entity);
        return interestCategoryMapper.toDto(savedInterestCategory);
    }

    @Override
    public List<InterestCategoryDto> saveAll(List<InterestCategoryDto> interestCategories) {
        return interestCategories.stream()
                .map(interestCategoryMapper::toEntity)
                .map(interestCategoryRepository::save)
                .map(interestCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        interestCategoryRepository.deleteById(id);
    }

    @Override
    public InterestCategoryDto findById(String id) {
        InterestCategory interestCategory = interestCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No InterestCategory found with id " + id));
        return interestCategoryMapper.toDto(interestCategory);
    }

    @Override
    public List<InterestCategoryDto> findAll() {
        List<InterestCategory> interestCategories = interestCategoryRepository.findAll();
        return interestCategories.stream().map(interestCategoryMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<InterestCategoryDto> findAll(Pageable pageable) {
        return interestCategoryRepository.findAll(pageable)
                .map(interestCategoryMapper::toDto);
    }

    @Override
    public InterestCategoryDto update(InterestCategoryDto interestCategory, String id) {
        if (!interestCategoryRepository.existsById(id)) {
            throw new RuntimeException("No InterestCategory found with id " + id);
        }
        interestCategory.setId(id);
        InterestCategory entity = interestCategoryMapper.toEntity(interestCategory);
        InterestCategory updatedInterestCategory = interestCategoryRepository.save(entity);
        return interestCategoryMapper.toDto(updatedInterestCategory);
    }
}
