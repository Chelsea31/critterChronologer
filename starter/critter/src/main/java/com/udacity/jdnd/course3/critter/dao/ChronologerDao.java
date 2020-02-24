package com.udacity.jdnd.course3.critter.dao;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.dao.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.dao.entity.PetEntity;
import com.udacity.jdnd.course3.critter.dao.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.dao.repository.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChronologerDao {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetsRepository petsRepository;

    public CustomerEntity addCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public PetEntity addPet(PetEntity pet) {
        return petsRepository.save(pet);
    }
}
