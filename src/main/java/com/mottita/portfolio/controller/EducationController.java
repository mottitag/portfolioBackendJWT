/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.controller;

import com.mottita.portfolio.model.Education;
import com.mottita.portfolio.service.EducationService;
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
@RequestMapping("/api/crud/edu")
public class EducationController {
    
    private EducationService educationService;
    
    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }
    
    @GetMapping(value = "bring", headers = "Accept=application/json")
    @ResponseBody
    public List<Education>bringEducation(){
        return this.educationService.findEducations();
    }
    
    @DeleteMapping(value = "del/{id}", headers = "Accept=application/json")
    public void deleteEducation(@PathVariable Long id){
        this.educationService.deleteEducation(id);
    }
    
    @PutMapping(value = "update", headers = "Accept=application/json")
    public void updateEducation(@RequestBody Education edu){        
        this.educationService.updateEducation(edu);
    }
}
