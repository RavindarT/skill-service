package com.example.skill;
import jakarta.validation.constraints.*;
public record SkillRequest(
    @NotBlank @Size(max = 255) String desc
) {}
