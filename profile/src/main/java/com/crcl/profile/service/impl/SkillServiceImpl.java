package com.crcl.profile.service.impl;

import com.crcl.profile.domain.Skill;
import com.crcl.profile.repository.SkillRepository;
import com.crcl.profile.service.SkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> saveAll(List<Skill> entitiesDto) {
        return skillRepository.saveAll(entitiesDto);
    }

    @Override
    public void deleteById(String id) {
        skillRepository.findById(id)
                .ifPresent(skill -> skillRepository.deleteById(id));
    }

    @Override
    public Skill findById(String id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("skill with id" + id + "not found "));
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Page<Skill> findAll(Pageable pageable) {
        return skillRepository.findAll(pageable);
    }

    @Override
    public Skill update(Skill skill, String id) {
        this.findById(id);
        return skillRepository.save(skill);
    }
}
