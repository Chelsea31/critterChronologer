package com.udacity.jdnd.course3.critter.dao;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.dao.entity.*;
import com.udacity.jdnd.course3.critter.dao.repository.*;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ChronologerDao {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetsRepository petsRepository;

    @Autowired
    DaysAvailableRepository daysAvailableRepository;

    @Autowired
    SkillsRepoistory skillsRepoistory;

    @Autowired
    EmployeeRepository employeeRepository;

    public CustomerEntity addCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public PetEntity addPet(PetEntity pet) {
        return petsRepository.save(pet);
    }

    public EmployeeEntity addEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    public List<PetEntity> getPetsByOwnerId(long ownerId) {
        return petsRepository.findAllByOwnerId(ownerId);
    }

    public CustomerEntity getOwnerByPetId(long petId) {
        Optional<PetEntity> petEntity = petsRepository.findById(petId);
        if (petEntity.isPresent()) {
            return customerRepository.findById(petEntity.get().getOwnerId()).get();
        }
        return null;
    }

    public void updateEmployee(Set<DayOfWeek> daysAvailable, long employeeId) {
        EmployeeEntity employeeEntity = getEmployee(employeeId);
        employeeEntity.setDaysAvailable(daysAvailable.stream()
                .map(dayOfWeek -> {
                    DaysAvailableEntity daysAvailableEntity = new DaysAvailableEntity();
                    daysAvailableEntity.setDayOfWeek(dayOfWeek);
                    daysAvailableEntity.setEmployee(employeeEntity);
                    return daysAvailableEntity;
                }).collect(Collectors.toSet()));
        employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity getEmployee(long employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    public List<EmployeeEntity> getEmployeeFromDate(DayOfWeek dayOfWeek) {
        List<DaysAvailableEntity> daysAvailable = daysAvailableRepository.findAllByDayOfWeek(dayOfWeek);
        return daysAvailable.stream()
                .map(employee -> getEmployee(employee.getEmployee().getEmployeeId()))
                .collect(Collectors.toList());
    }

    public DaysAvailableEntity getEmployeeFromDateAndEmployeeId(long employeeId, DayOfWeek dayOfWeek){
        return daysAvailableRepository.findByEmployeeEmployeeIdAndDayOfWeek(employeeId, dayOfWeek);
    }

    public Set<EmployeeEntity> getEmployeeFromSkill(Set<EmployeeSkill> skills) {
        Set<SkillsEntity> employeeBySkillIn = skillsRepoistory.findEmployeeBySkillIn(skills);
        return employeeBySkillIn.stream().map(SkillsEntity::getEmployee).collect(Collectors.toSet());
    }
}
