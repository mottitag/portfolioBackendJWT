/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.service;

import com.mottita.portfolio.model.Skill;
import com.mottita.portfolio.repository.ISkillRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {
    
    private ISkillRepository skillRepo;
    
    @Autowired

    public SkillService(ISkillRepository skillRepo) {
        this.skillRepo = skillRepo;
    }
    
    public List<Skill> findSkills() {
        return this.skillRepo.findAll();
    }

    public void updateSkill(Skill skill){
        this.skillRepo.save(skill);
    }

    public void deleteSkill(Long id) {
        this.skillRepo.deleteById(id);
    }

    public Optional<Skill> findSkill(Long id) {
        return this.skillRepo.findById(id);
    }
}
