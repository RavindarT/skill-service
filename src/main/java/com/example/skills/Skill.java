package com.example.skills;
import jakarta.persistence.*;
@Entity
@Table(name = "skill")
public class Skill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private Long sId;
    @Column(name = "description", nullable = false, length = 255)
    private String desc;
    public Long getSId() { return sId; }
    public void setSId(Long value) { sId = value; }
    public String getDesc() { return desc; }
    public void setDesc(String value) { desc = value; }
}
