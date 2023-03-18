package com.crcl.post.actionValidators;

import com.crcl.post.dto.PostDto;
import com.crcl.post.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class CanDeleteActionValidatorImplTest {
    @InjectMocks
    private CanDeleteActionValidatorImpl canDeleteActionValidator;
    @Mock
    private UserService userService;

    @Test
    public void validate() {
        PostDto postDto = CrclTestUtils.buildPostDto("bla bla");

        canDeleteActionValidator.validate(postDto);

        assertTrue(postDto.getActions().get(Actions.DELETE));
    }
}