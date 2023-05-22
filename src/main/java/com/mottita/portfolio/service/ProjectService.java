/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.service;

import com.mottita.portfolio.model.Project;
import com.mottita.portfolio.repository.IProjectRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    
    private IProjectRepository proRepo;
    
    @Autowired

    public ProjectService(IProjectRepository proRepo) {
        this.proRepo = proRepo;
    }
    
    public List<Project> findProjects() {
        return this.proRepo.findAll();
    }

    public void deleteProject(Long id) {
        this.proRepo.deleteById(id);
    }

    public Optional<Project> findProject(Long id) {
        return this.proRepo.findById(id);
    }

    public void updateProject(Project pro) {
        this.proRepo.save(pro);
    }
    
}
