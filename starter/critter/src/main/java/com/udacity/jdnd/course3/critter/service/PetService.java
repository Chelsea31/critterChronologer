package com.udacity.jdnd.course3.critter.service;

/*
 * @author Shubham Bansal
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.jdnd.course3.critter.dao.ChronologerDao;
import com.udacity.jdnd.course3.critter.dao.entity.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    ChronologerDao chronologerDao;

    @Autowired
    ObjectMapper mapper;

    public PetDTO savePet(PetDTO petDTO) {
        PetEntity petEntity = chronologerDao.addPet(mapper.convertValue(petDTO, PetEntity.class));
        return mapper.convertValue(petEntity, PetDTO.class);
    }

    public PetDTO getPet(int petId) {
        throw new UnsupportedOperationException();
    }

    public List<PetDTO> getPets() {
        throw new UnsupportedOperationException();
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        return chronologerDao.getPetsByOwnerId(ownerId).stream()
                .map(petEntity -> mapper.convertValue(petEntity, PetDTO.class))
                .collect(Collectors.toList());
    }
}
