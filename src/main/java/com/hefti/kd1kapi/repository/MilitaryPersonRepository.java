package com.hefti.kd1kapi.repository;

import com.hefti.kd1kapi.model.MilitaryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MilitaryPersonRepository extends JpaRepository<MilitaryPerson,Long> {

    Optional<MilitaryPerson> findById(Long id);

    List<MilitaryPerson> findByCpf(String cpf);

    List<MilitaryPerson> findByFullNameContaining(String name);

}
