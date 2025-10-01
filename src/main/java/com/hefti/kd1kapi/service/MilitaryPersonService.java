package com.hefti.kd1kapi.service;

import com.hefti.kd1kapi.dto.MilitaryPersonDTO;
import com.hefti.kd1kapi.model.MilitaryPerson;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MilitaryPersonService {

    List<MilitaryPersonDTO> getAllPerson();

    MilitaryPersonDTO createPerson(MilitaryPersonDTO peopleDTO);

    MilitaryPersonDTO getPersonById(Long id);

    MilitaryPersonDTO getPersonByCpf(String cpf);

    List<MilitaryPersonDTO> getPersonByName(String name);

    MilitaryPersonDTO updatePerson(Long id, MilitaryPersonDTO peopleDTO);

    void deletePerson(Long id);
    
    
}
