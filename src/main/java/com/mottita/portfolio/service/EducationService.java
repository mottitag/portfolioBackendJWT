/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.service;

import com.mottita.portfolio.model.Education;
import com.mottita.portfolio.repository.IEducationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
    
    private IEducationRepository eduRepo;
    
    @Autowired
    public EducationService(IEducationRepository eduRepo) {
        this.eduRepo = eduRepo;
    }
    
    public List<Education> findEducations(){
        return this.eduRepo.findAll();
    }
    
    public void updateEducation(Education edu){
        this.eduRepo.save(edu);
    }
    
    public void deleteEducation(Long id) {
        this.eduRepo.deleteById(id);
    }
    
    public Optional<Education> findEducation(Long id) {
        return this.eduRepo.findById(id);
    }
}
