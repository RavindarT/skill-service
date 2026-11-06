package com.example.skill;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SkillService {
    private final SkillRepository repository;
    public SkillService(SkillRepository repository) { this.repository = repository; }
    @Transactional(readOnly = true)
    public List<Skill> list() { return repository.findAll(); }
    @Transactional(readOnly = true)
    public Skill get(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Skill not found")); }
    @Transactional
    public Skill create(SkillRequest request) {

        Skill record = new Skill();
        record.setDesc(request.desc());
        return repository.save(record);
    }
    @Transactional
    public Skill update(Long id, SkillRequest request) {
        Skill record = get(id);
        record.setDesc(request.desc());
        return repository.save(record);
    }
    @Transactional
    public void delete(Long id) { repository.delete(get(id)); }
}
