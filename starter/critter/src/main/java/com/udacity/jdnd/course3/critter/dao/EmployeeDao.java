package com.udacity.jdnd.course3.critter.dao;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.dao.entity.DaysAvailableEntity;
import com.udacity.jdnd.course3.critter.dao.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.dao.entity.SkillsEntity;
import com.udacity.jdnd.course3.critter.dao.repository.DaysAvailableRepository;
import com.udacity.jdnd.course3.critter.dao.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.dao.repository.SkillsRepoistory;
import com.udacity.jdnd.course3.critter.model.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeDao {
    @Autowired
    DaysAvailableRepository daysAvailableRepository;

    @Autowired
    SkillsRepoistory skillsRepoistory;

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeEntity addEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
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

    public DaysAvailableEntity getEmployeeFromDateAndEmployeeId(long employeeId, DayOfWeek dayOfWeek) {
        return daysAvailableRepository.findByEmployeeEmployeeIdAndDayOfWeek(employeeId, dayOfWeek);
    }

    public Set<EmployeeEntity> getEmployeeFromSkill(Set<EmployeeSkill> skills) {
        Set<SkillsEntity> employeeBySkillIn = skillsRepoistory.findEmployeeBySkillIn(skills);
        return employeeBySkillIn.stream().map(SkillsEntity::getEmployee).collect(Collectors.toSet());
    }
}
