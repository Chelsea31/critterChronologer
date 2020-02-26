package com.udacity.jdnd.course3.critter.service;

/*
 * @author Shubham Bansal
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.jdnd.course3.critter.dao.PetDao;
import com.udacity.jdnd.course3.critter.dao.entity.PetEntity;
import com.udacity.jdnd.course3.critter.model.pet.PetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    PetDao petDao;

    @Autowired
    ObjectMapper mapper;

    public PetDTO savePet(PetDTO petDTO) {
        PetEntity petEntity = petDao.addPet(mapper.convertValue(petDTO, PetEntity.class));
        return mapper.convertValue(petEntity, PetDTO.class);
    }

    public PetDTO getPet(int petId) {
        return mapper.convertValue(petDao.getPetById(petId), PetDTO.class);
    }

    public List<PetDTO> getPets() {
        return petDao.getAllPets().stream().map(pet -> mapper.convertValue(pet, PetDTO.class)).collect(Collectors.toList());
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        return petDao.getPetsByOwnerId(ownerId).stream()
                .map(petEntity -> mapper.convertValue(petEntity, PetDTO.class))
                .collect(Collectors.toList());
    }
}
