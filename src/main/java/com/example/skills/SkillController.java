package com.example.skills;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final JdbcClient db;

    public SkillController(JdbcClient db) {
        this.db = db;
    }

    public record Skill(UUID id, String name, String description) {}

    public record SkillInput(@NotBlank @Size(max = 120) String name,
                           @Size(max = 2000) String description) {}

    @PostMapping
    public ResponseEntity<Skill> create(@Valid @RequestBody SkillInput input) {
        Skill skill = new Skill(UUID.randomUUID(), input.name().strip(), input.description());
        db.sql("INSERT INTO skills (id, name, description) VALUES (:id, :name, :description)")
                .paramSource(skill).update();
        return ResponseEntity.created(URI.create("/api/skills/" + skill.id())).body(skill);
    }

    @GetMapping
    public List<Skill> list() {
        return db.sql("SELECT id, name, description FROM skills ORDER BY name, id")
                .query(Skill.class).list();
    }

    @GetMapping("/{id}")
    public Skill get(@PathVariable UUID id) {
        return db.sql("SELECT id, name, description FROM skills WHERE id = ?")
                .param(id).query(Skill.class).optional().orElseThrow(SkillController::notFound);
    }

    @PutMapping("/{id}")
    public Skill update(@PathVariable UUID id, @Valid @RequestBody SkillInput input) {
        Skill skill = new Skill(id, input.name().strip(), input.description());
        requireSkill(db.sql("UPDATE skills SET name = :name, description = :description WHERE id = :id")
                .paramSource(skill).update());
        return skill;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        requireSkill(db.sql("DELETE FROM skills WHERE id = ?").param(id).update());
    }

    private static void requireSkill(int rows) {
        if (rows == 0) {
            throw notFound();
        }
    }

    private static ResponseStatusException notFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found");
    }
}
