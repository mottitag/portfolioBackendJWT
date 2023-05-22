/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.controller;

import com.mottita.portfolio.model.Experience;
import com.mottita.portfolio.service.ExperienceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crud/exp")
public class ExperienceController {
    
    private ExperienceService  experienceService;
    
    @Autowired
    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }
    
    @GetMapping(value = "bring", headers = "Accept=application/json")
    @ResponseBody
    public List<Experience> bringExperiences(){
        return this.experienceService.findExperiences();
    }
    
    @DeleteMapping(value = "del/{id}", headers = "Accept=application/json")
    public void deleteExperience(@PathVariable Long id){
        this.experienceService.deleteExperience(id);
    }
    
    @PutMapping(value = "update", headers = "Accept=application/json")
    public void updateExperience(@RequestBody Experience exp){        
        this.experienceService.updateExperience(exp);
    }
}
