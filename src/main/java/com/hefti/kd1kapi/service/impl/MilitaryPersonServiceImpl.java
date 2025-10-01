package com.hefti.kd1kapi.service.impl;

import com.hefti.kd1kapi.dto.MilitaryPersonDTO;
import com.hefti.kd1kapi.model.MilitaryPerson;
import com.hefti.kd1kapi.repository.MilitaryPersonRepository;
import com.hefti.kd1kapi.service.MilitaryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MilitaryPersonServiceImpl implements MilitaryPersonService {

    private final MilitaryPersonRepository militaryPersonRepository;

    @Autowired
    public MilitaryPersonServiceImpl(MilitaryPersonRepository militaryPersonRepository) {
        this.militaryPersonRepository = militaryPersonRepository;
    }

    @Override
    @Transactional
    public List<MilitaryPersonDTO> getAllPerson() {
        List<MilitaryPerson> militaryPeople = militaryPersonRepository.findAll();
        return militaryPeople.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MilitaryPersonDTO createPerson(MilitaryPersonDTO peopleDTO) {
        MilitaryPerson militaryPerson = convertToEntity(peopleDTO);
        MilitaryPerson savedPerson = militaryPersonRepository.save(militaryPerson);
        return convertToDTO(savedPerson);
    }

    @Override
    @Transactional(readOnly = true)
    public MilitaryPersonDTO getPersonById(Long id) {
        MilitaryPerson militaryPerson = militaryPersonRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return convertToDTO(militaryPerson);
    }

    @Override
    @Transactional(readOnly = true)
    public MilitaryPersonDTO getPersonByCpf(String cpf) {
        MilitaryPerson militaryPerson = militaryPersonRepository.findByCpf(cpf)
                .stream()
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
        return convertToDTO(militaryPerson);
    }

    @Override
    public List<MilitaryPersonDTO> getPersonByName(String name) {
        List<MilitaryPerson> militaryPeople = militaryPersonRepository.findByNameContaining(name);
        List<MilitaryPersonDTO> militaryPeopleDTO = militaryPeople.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (militaryPeopleDTO.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return militaryPeopleDTO;
    }

    @Override
    @Transactional
    public MilitaryPersonDTO updatePerson(Long id, MilitaryPersonDTO peopleDTO) {
        MilitaryPerson militaryPerson = militaryPersonRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        updateEntityFields(militaryPerson, peopleDTO);
        MilitaryPerson updatedPerson = militaryPersonRepository.save(militaryPerson);
        return convertToDTO(updatedPerson);
    }

    @Override
    @Transactional
    public void deletePerson(Long id) {
        MilitaryPerson militaryPerson = militaryPersonRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        militaryPersonRepository.delete(militaryPerson);
    }

    private MilitaryPersonDTO convertToDTO(MilitaryPerson entity) {
        if (entity == null) {
            return null;
        }
        return MilitaryPersonDTO.builder().
                id(entity.getId()).
                fullName(entity.getFullName()).
                cpf(entity.getCpf()).
                birthDate(entity.getBirthDate()).
                build();
    }

    private MilitaryPerson convertToEntity(MilitaryPersonDTO dto) {
        if (dto == null) {
            return null;
        }
        MilitaryPerson people = new MilitaryPerson();
        people.setId(dto.id());
        people.setFullName(dto.fullName());
        people.setCpf(dto.cpf());
        people.setBirthDate(dto.birthDate());
        return people;
    }

    private void updateEntityFields(MilitaryPerson entity, MilitaryPersonDTO dto) {
        if (dto == null) {
            return;
        }
        entity.setFullName(dto.fullName());
        entity.setCpf(dto.cpf());
        entity.setBirthDate(dto.birthDate());
    }
}
