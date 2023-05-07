package com.crcl.authentication.repository;

import com.crcl.authentication.domain.GramifyUser;
import com.crcl.authentication.utils.UserGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GramifyUserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void testFindByUsernameAllIgnoreCase() {
        // Add a user with a known username to the database
        GramifyUser gramifyUser = new GramifyUser();
        gramifyUser.setUsername("test_username");
        userRepository.save(gramifyUser);
        List<GramifyUser> gramifyUsers = UserGenerator.generateRandomUsers(100, "USERNAME");
        userRepository.saveAll(gramifyUsers);
        // Search for the user using a variety of different cases for the username
        Optional<GramifyUser> result1 = userRepository.findByUsernameAllIgnoreCase("USERNAME1");
        Optional<GramifyUser> result2 = userRepository.findByUsernameAllIgnoreCase("USERNAME2");
        Optional<GramifyUser> result3 = userRepository.findByUsernameAllIgnoreCase("USERNAME3");
        Optional<GramifyUser> result4 = userRepository.findByUsernameAllIgnoreCase("USERNAME4");

        // Verify that the user was found in all cases
        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
        assertTrue(result3.isPresent());
        assertTrue(result4.isPresent());
    }
}