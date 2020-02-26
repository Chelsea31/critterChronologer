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

import java.util.List;
import java.util.Optional;

@Component
public class CustomerDao {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetsRepository petsRepository;

    public CustomerEntity addCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerEntity getOwnerByPetId(long petId) {
        Optional<PetEntity> petEntity = petsRepository.findById(petId);
        if (petEntity.isPresent()) {
            return customerRepository.findById(petEntity.get().getOwnerId()).get();
        }
        return null;
    }
}
