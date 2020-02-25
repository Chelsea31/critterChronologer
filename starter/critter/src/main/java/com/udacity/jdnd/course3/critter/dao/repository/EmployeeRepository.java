package com.udacity.jdnd.course3.critter.dao.repository;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.dao.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.dao.entity.SkillsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findAllByDaysAvailable(DayOfWeek dayOfWeek);

    List<EmployeeEntity> findBySkillsIn(Set<SkillsEntity> skills);
}
