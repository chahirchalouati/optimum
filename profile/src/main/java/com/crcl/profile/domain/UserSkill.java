package com.crcl.profile.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserSkill {
    private Skill skill;
    private Proficiency proficiency;
}
