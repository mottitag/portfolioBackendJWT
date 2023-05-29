/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mottita.portfolio.service;

import com.mottita.portfolio.model.Person;
import com.mottita.portfolio.repository.IPersonRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    
    private IPersonRepository perRepo;
    
    @Autowired
    public PersonService(IPersonRepository perRepo) {
        this.perRepo = perRepo;
    }
    
    public List<Person> findPersons() {
        return this.perRepo.findAll();
    }

    public void createPerson(Person per) {
        this.perRepo.save(per);
    }
    
    public Person updatePerson(Person per){
        return this.perRepo.save(per);
    }

    public void deletePerson(Long id) {
        this.perRepo.deleteById(id);
    }

    public Optional<Person> findPerson(Long id) {
        return this.perRepo.findById(id);
    }
}
