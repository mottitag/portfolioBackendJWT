/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.controller;

import com.mottita.portfolio.model.PService;
import com.mottita.portfolio.service.PServiceService;
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
@RequestMapping("/api/crud/serv")
public class PServiceController {
    
    private PServiceService pServiceService;
    
    @Autowired
    public PServiceController(PServiceService pServiceService) {
        this.pServiceService = pServiceService;
    }
    
    @GetMapping(value = "bring", headers = "Accept=application/json")
    @ResponseBody
    public List<PService> bringServices (){
        return this.pServiceService.findServices();
    }
    
    @DeleteMapping(value = "del/{id}", headers = "Accept=application/json")
    public void deleteService(@PathVariable Long id){
        this.pServiceService.deleteService(id);
    }
    
    @PutMapping(value = "update", headers = "Accept=application/json")
    public void updateService(@RequestBody PService serv){
        this.pServiceService.updateService(serv);  
    }
}
