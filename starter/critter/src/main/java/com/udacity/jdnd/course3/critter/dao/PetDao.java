package com.udacity.jdnd.course3.critter.dao;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.dao.entity.PetEntity;
import com.udacity.jdnd.course3.critter.dao.repository.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetDao {


    @Autowired
    PetsRepository petsRepository;


    public PetEntity addPet(PetEntity pet) {
        return petsRepository.save(pet);
    }


    public PetEntity getPetById(long petId) {
        return petsRepository.findById(petId).get();
    }

    public List<PetEntity> getAllPets() {
        return petsRepository.findAll();
    }

    public List<PetEntity> getPetsByOwnerId(long ownerId) {
        return petsRepository.findAllByOwnerId(ownerId);
    }

}
