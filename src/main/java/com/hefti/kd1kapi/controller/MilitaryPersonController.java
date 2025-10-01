package com.hefti.kd1kapi.controller;

import com.hefti.kd1kapi.dto.MilitaryPersonDTO;
import com.hefti.kd1kapi.service.MilitaryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/military-person")
public class MilitaryPersonController {
    
    
    @Autowired
    private MilitaryPersonService militaryPersonService;

    @PostMapping    
    public ResponseEntity<MilitaryPersonDTO> createPerson(@RequestBody MilitaryPersonDTO personDTO) {
        MilitaryPersonDTO createdPerson = militaryPersonService.createPerson(personDTO);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")    
    public ResponseEntity<MilitaryPersonDTO> getPersonById(@PathVariable Long id) {
        MilitaryPersonDTO personDTO = militaryPersonService.getPersonById(id);
        return ResponseEntity.ok(personDTO);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<MilitaryPersonDTO> getPersonByCpf(@PathVariable String cpf) {
        MilitaryPersonDTO personDTO = militaryPersonService.getPersonByCpf(cpf);
        return ResponseEntity.ok(personDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<MilitaryPersonDTO>> getPersonByName(@PathVariable String name) {
        List<MilitaryPersonDTO> people = militaryPersonService.getPersonByName(name);
        return ResponseEntity.ok(people);
    }

    @GetMapping
    public ResponseEntity<List<MilitaryPersonDTO>> getAllPerson() {
        List<MilitaryPersonDTO> people = militaryPersonService.getAllPerson();
        return ResponseEntity.ok(people);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MilitaryPersonDTO> updatePerson(@PathVariable Long id,@RequestBody MilitaryPersonDTO personDTO) {
        MilitaryPersonDTO updatedPerson = militaryPersonService.updatePerson(id, personDTO);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        militaryPersonService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

}
