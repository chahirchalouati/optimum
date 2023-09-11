package com.crcl.authentication.repository;

import com.crcl.authentication.domain.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoleRepositoryTest extends BaseRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
    }

    @Test
    public void givenNewRole_whenSaved_thenShouldFindRoleByName() {
        Role role = new Role("ROLE_USER");
        roleRepository.save(role);
        Optional<Role> foundRole = roleRepository.findByName("ROLE_USER");
        assertTrue(foundRole.isPresent());
        assertEquals("ROLE_USER", foundRole.get().getName());
    }

    @Test
    public void givenNonExistentRole_whenSearched_thenShouldNotFindRole() {
        Optional<Role> foundRole = roleRepository.findByName("NON_EXISTENT_ROLE");
        assertFalse(foundRole.isPresent());
    }

    @Test
    public void givenSavedRole_whenDeleted_thenShouldNotFindRole() {
        Role role = new Role("ROLE_ADMIN");
        roleRepository.save(role);
        roleRepository.delete(role);
        Optional<Role> foundRole = roleRepository.findByName("ROLE_ADMIN");
        assertFalse(foundRole.isPresent());
    }
}