/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.service;

import com.mottita.portfolio.model.PService;
import com.mottita.portfolio.repository.IPServiceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PServiceService {
    
    private IPServiceRepository servRepo;
    
    @Autowired
    public PServiceService(IPServiceRepository servRepo) {
        this.servRepo = servRepo;
    }
    
    public List<PService> findServices() {
        return servRepo.findAll();
    }

    public void deleteService(Long id) {
        servRepo.deleteById(id);
    }

    public Optional<PService> findService(Long id) {
        return servRepo.findById(id);
    }

    public void updateService(PService serv) {
        this.servRepo.save(serv);
    }
}
