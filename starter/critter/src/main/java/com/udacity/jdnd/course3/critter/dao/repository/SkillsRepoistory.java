package com.udacity.jdnd.course3.critter.dao.repository;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.dao.entity.SkillsEntity;
import com.udacity.jdnd.course3.critter.model.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SkillsRepoistory extends JpaRepository<SkillsEntity, Long> {
    Set<SkillsEntity> findEmployeeBySkillIn(Set<EmployeeSkill> skill);
}
