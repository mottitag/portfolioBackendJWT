/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.controller;

import com.mottita.portfolio.model.Project;
import com.mottita.portfolio.service.ProjectService;
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
@RequestMapping("/api/crud/pro")
public class ProjectController {
    
    private ProjectService projectService;
    
    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    
    @GetMapping(value = "bring", headers = "Accept=application/json")
    @ResponseBody
    public List<Project> bringProjects(){
        return this.projectService.findProjects();
    }
    
    @DeleteMapping(value = "del/{id}", headers = "Accept=application/json")
    public void deleteProject(@PathVariable Long id){
        this.projectService.deleteProject(id);
    }
    
    @PutMapping(value = "update", headers = "Accept=application/json")
    public void updateProject (@RequestBody Project pro){
        this.projectService.updateProject(pro);
    }
}
