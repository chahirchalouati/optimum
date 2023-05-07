package com.crcl.post.annotations;

import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.utils.CrclFileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreatePostRequestValidator implements ConstraintValidator<ValidCreatePostRequest, CreatePostRequest> {
    @Override
    public boolean isValid(CreatePostRequest request, ConstraintValidatorContext constraintValidatorContext) {
        return CrclFileUtils.hasFiles(request.getFiles()) || !StringUtils.isBlank(request.getContent());
    }
}
