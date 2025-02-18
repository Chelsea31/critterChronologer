package com.udacity.jdnd.course3.critter.service;

/*
 * @author Shubham Bansal
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.jdnd.course3.critter.dao.CustomerDao;
import com.udacity.jdnd.course3.critter.dao.EmployeeDao;
import com.udacity.jdnd.course3.critter.dao.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.dao.entity.DaysAvailableEntity;
import com.udacity.jdnd.course3.critter.dao.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.dao.entity.SkillsEntity;
import com.udacity.jdnd.course3.critter.model.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.model.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.model.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.model.user.EmployeeSkill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    ObjectMapper mapper;

    public List<CustomerDTO> getAllCustomers() {
        return customerDao.getAllCustomers().stream().map(customerEntity -> mapper.convertValue(customerEntity, CustomerDTO.class)).collect(Collectors.toList());
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        try {
            CustomerEntity savedCustomerEntity = customerDao.addCustomer(mapper.convertValue(customerDTO, CustomerEntity.class));
            return mapper.convertValue(savedCustomerEntity, CustomerDTO.class);
        } catch (Exception e) {
            log.error("Could not save customer : {}, exception : {}", customerDTO, e);
            return null;
        }
    }

    public CustomerDTO getOwnerByPet(long petId) {
        return mapper.convertValue(customerDao.getOwnerByPetId(petId), CustomerDTO.class);
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        log.info("Adding employee : {}", employeeDTO);
        try {
            EmployeeEntity employeeEntity = employeeDao.addEmployee(mapEmployeeDtoToEntity(employeeDTO));
            return mapEmployeeEntityToDto(employeeEntity);
        } catch (Exception e) {
            log.error("Error in adding employee : {}, exception : {}", employeeDTO, e);
            return null;
        }
    }

    EmployeeDTO mapEmployeeEntityToDto(EmployeeEntity employeeEntity) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        if (employeeEntity.getDaysAvailable() != null)
            employeeDTO.setDaysAvailable(employeeEntity.getDaysAvailable().stream()
                    .map(daysAvailableEntity -> daysAvailableEntity.getDayOfWeek())
                    .collect(Collectors.toSet()));
        employeeDTO.setName(employeeEntity.getName());
        if (employeeEntity.getSkills() != null)
            employeeDTO.setSkills(employeeEntity.getSkills().stream()
                    .map(skillsEntity -> skillsEntity.getSkill())
                    .collect(Collectors.toSet()));
        employeeDTO.setId(employeeEntity.getEmployeeId());
        return employeeDTO;
    }

    EmployeeEntity mapEmployeeDtoToEntity(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employeeDTO.getName());
        employeeEntity.setEmployeeId(employeeDTO.getId());
        if (employeeDTO.getSkills() != null)
            employeeEntity.setSkills(employeeDTO.getSkills().stream().map(skill -> {
                SkillsEntity skillsEntity = new SkillsEntity();
                skillsEntity.setSkill(skill);
                skillsEntity.setEmployee(employeeEntity);
                return skillsEntity;
            }).collect(Collectors.toSet()));
        if (employeeDTO.getDaysAvailable() != null)
            employeeEntity.setDaysAvailable(employeeDTO.getDaysAvailable().stream().map(dayOfWeek -> {
                DaysAvailableEntity daysAvailableEntity = new DaysAvailableEntity();
                daysAvailableEntity.setDayOfWeek(dayOfWeek);
                daysAvailableEntity.setEmployee(employeeEntity);
                return daysAvailableEntity;
            }).collect(Collectors.toSet()));
        return employeeEntity;
    }

    public EmployeeDTO getEmployee(long employeeId) {
        return mapEmployeeEntityToDto(employeeDao.getEmployee(employeeId));
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        employeeDao.updateEmployee(daysAvailable, employeeId);
    }

    public Set<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> skill = employeeDTO.getSkills();
        Set<EmployeeEntity> employeeFromSkill = employeeDao.getEmployeeFromSkill(skill);
        Set<DaysAvailableEntity> employeesFromDaysAvailableEntities = employeeFromSkill.stream()
                .map(EmployeeEntity::getEmployeeId)
                .map(id -> employeeDao.getEmployeeFromDateAndEmployeeId(id, dayOfWeek))
                .collect(Collectors.toSet());
        return employeesFromDaysAvailableEntities.stream()
                .map(DaysAvailableEntity::getEmployee)
                .map(this::mapEmployeeEntityToDto)
                .collect(Collectors.toSet());
    }
}
