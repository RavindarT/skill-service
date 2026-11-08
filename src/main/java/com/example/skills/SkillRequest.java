package com.example.skills;
import jakarta.validation.constraints.*;
public record SkillRequest(
    @NotBlank @Size(max = 255) String desc
) {}
