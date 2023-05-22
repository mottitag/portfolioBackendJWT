/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.service;

import com.mottita.portfolio.model.Experience;
import com.mottita.portfolio.repository.IExperienceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {
    
    private IExperienceRepository expRepo;
    
    @Autowired
    public ExperienceService(IExperienceRepository expRepo) {
        this.expRepo = expRepo;
    }
    
    public List<Experience> findExperiences() {
        return expRepo.findAll();
    }

    public void deleteExperience(Long id) {
        expRepo.deleteById(id);
    }

    public Optional<Experience> findExperience(Long id) {
        return expRepo.findById(id);
    }

    public void updateExperience(Experience exp) {
        expRepo.save(exp);
    }
}
