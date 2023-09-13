package com.crcl.post.annotations;

import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.utils.CrclFileUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;


public class CreatePostRequestValidator implements ConstraintValidator<HasPostContentOrFiles, CreatePostRequest> {
    @Override
    public boolean isValid(CreatePostRequest request, ConstraintValidatorContext constraintValidatorContext) {
        return CrclFileUtils.hasFiles(request.getFiles()) || !StringUtils.isBlank(request.getContent());
    }
}
