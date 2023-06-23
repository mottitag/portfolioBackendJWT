/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String img;
    private String title;
    private String description;
    private String phone;
    @OneToMany(targetEntity = Project.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "per_fk", referencedColumnName = "id")
    private List<Project> projects;
    @OneToMany(targetEntity = Experience.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "per_fk", referencedColumnName = "id")
    private List<Experience> experiences;
    @OneToMany(targetEntity = Education.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "per_fk", referencedColumnName = "id")
    private List<Education> educations;
    @OneToMany(targetEntity = PService.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pr_fk", referencedColumnName = "id")
    private List<PService> pservices;
    @OneToMany(targetEntity = Skill.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "per_fk", referencedColumnName = "id")
    private List<Skill> skills;
    
    public void addEducation (Education edu){
        this.educations.add(edu);
    }
    
    public void addExperience (Experience exp){
        this.experiences.add(exp);
    }
    
    public void addProject (Project pro){
        this.projects.add(pro);
    }
    
    public void addPService (PService serv){
        this.pservices.add(serv);
    }
    
    public void addSkill (Skill ski){
        this.skills.add(ski);
    }
    
}
