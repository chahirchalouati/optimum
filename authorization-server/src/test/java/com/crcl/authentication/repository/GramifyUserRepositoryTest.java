package com.crcl.authentication.repository;

import com.crcl.authentication.domain.GramifyUser;
import com.crcl.authentication.utils.UserGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class GramifyUserRepositoryTest extends BaseRepositoryConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void testFindByUsernameAllIgnoreCase() {
        GramifyUser gramifyUser = new GramifyUser().setUsername("test_username");
        userRepository.save(gramifyUser);

        List<GramifyUser> gramifyUsers = UserGenerator.generateRandomUsers(100, "USERNAME");
        userRepository.saveAll(gramifyUsers);

        Optional<GramifyUser> result1 = userRepository.findByUsernameAllIgnoreCase("USERNAME1");
        Optional<GramifyUser> result2 = userRepository.findByUsernameAllIgnoreCase("USERNAME2");
        Optional<GramifyUser> result3 = userRepository.findByUsernameAllIgnoreCase("USERNAME3");
        Optional<GramifyUser> result4 = userRepository.findByUsernameAllIgnoreCase("USERNAME4");

        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
        assertTrue(result3.isPresent());
        assertTrue(result4.isPresent());
    }
}