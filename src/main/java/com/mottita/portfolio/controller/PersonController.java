/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.controller;

import com.mottita.portfolio.dto.DtoHome;
import com.mottita.portfolio.dto.DtoProfile;
import com.mottita.portfolio.model.Education;
import com.mottita.portfolio.model.Experience;
import com.mottita.portfolio.model.PService;
import com.mottita.portfolio.model.Person;
import com.mottita.portfolio.model.Project;
import com.mottita.portfolio.model.Skill;
import com.mottita.portfolio.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crud/per")
public class PersonController {
    
    private PersonService personService;
    
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    
    @PostMapping(value = "new", headers = "Accept=application/json")
    public void addPerson (@RequestBody Person per){
        this.personService.createPerson(per);
    }
    
    @GetMapping ("bring")
    @ResponseBody
    public List<Person>bringPersons(){
        return this.personService.findPersons();
    }
    
    @GetMapping (value = "bringHome/{id}", headers = "Accept=application/json")
    @ResponseBody
    public DtoHome bringHome (@PathVariable Long id){
        Person per = this.personService.findPerson(id).get();
        DtoHome home = new DtoHome();
        home.setTitle(per.getTitle());
        home.setDescription(per.getDescription());
        home.setPhone(per.getPhone());
        
        return home;
    }
    
    @GetMapping (value = "bringProf/{id}", headers = "Accept=application/json")
    @ResponseBody
    public DtoProfile bringProfile (@PathVariable Long id){
        Person per = this.personService.findPerson(id).get();
        DtoProfile prof = new DtoProfile();
        prof.setPhoto(per.getImg());
        prof.setName(per.getName());
        
        return prof;
    }
    
    @PostMapping (value = "newEdu/{idPer}", headers = "Accept=application/json")
    public Education addEducation (@PathVariable Long idPer, @RequestBody Education edu){
        Person per = this.personService.findPerson(idPer).get();
        per.addEducation(edu);
        this.personService.updatePerson(per);
        per = this.personService.updatePerson(per);
        return per.getEducations().get(per.getEducations().size() - 1);
    }
    
    @PostMapping (value = "newExp/{idPer}", headers = "Accept=application/json")
    public Experience addExperience (@PathVariable Long idPer, @RequestBody Experience exp){
        Person per = this.personService.findPerson(idPer).get();
        per.addExperience(exp);
        this.personService.updatePerson(per);
        per = this.personService.updatePerson(per);
        return per.getExperiences().get(per.getExperiences().size() - 1);
    }
    
    @PostMapping (value = "newPro/{idPer}", headers = "Accept=application/json")
    public Project addProject (@PathVariable Long idPer, @RequestBody Project pro){
        Person per = this.personService.findPerson(idPer).get();
        per.addProject(pro);
        this.personService.updatePerson(per);
        per = this.personService.updatePerson(per);
        return per.getProjects().get(per.getProjects().size() - 1);
    }
    
    @PostMapping (value = "newServ/{idPer}", headers = "Accept=application/json")
    public PService addService (@PathVariable Long idPer, @RequestBody PService ser){
        Person per = this.personService.findPerson(idPer).get();
        per.addPService(ser);
        per = this.personService.updatePerson(per);
        return per.getPservices().get(per.getPservices().size() - 1);
    }
    
    @PostMapping (value = "newSkill/{idPer}", headers = "Accept=application/json")
    public Skill addSkill (@PathVariable Long idPer, @RequestBody Skill ski){
        Person per = this.personService.findPerson(idPer).get();
        per.addSkill(ski);
        this.personService.updatePerson(per);
        per = this.personService.updatePerson(per);
        return per.getSkills().get(per.getSkills().size() - 1);
    }
    
    @PutMapping (value = "updateHome/{id}", headers = "Accept=application/json")
    public void updatePerson (@PathVariable Long id, @RequestBody DtoHome home){
        Person per = this.personService.findPerson(id).get();
        per.setTitle(home.getTitle());
        per.setDescription(home.getDescription());
        per.setPhone(home.getPhone());
        this.personService.updatePerson(per);
    }
    
    @DeleteMapping (value = "delete/{id}", headers = "Accept=application/json")
    public void deletePerson(@PathVariable Long id){
        this.personService.deletePerson(id);
    }
}
