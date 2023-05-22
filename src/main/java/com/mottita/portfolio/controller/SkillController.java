/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.controller;

import com.mottita.portfolio.model.Skill;
import com.mottita.portfolio.service.SkillService;
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
@RequestMapping("/api/crud/skill")
public class SkillController {
    
    private SkillService skillService;
    
    @Autowired

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }
    
    @GetMapping(value = "bring", headers = "Accept=application/json")
    @ResponseBody
    public List<Skill> bringServices (){
        return this.skillService.findSkills();
    }
    
    @DeleteMapping(value = "del/{id}", headers = "Accept=application/json")
    public void deleteService(@PathVariable Long id){
        this.skillService.deleteSkill(id);
    }
    
    @PutMapping(value = "update", headers = "Accept=application/json")
    public void updateService(@RequestBody Skill skill){
        this.skillService.updateSkill(skill);
    }
}
