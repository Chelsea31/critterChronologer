package com.udacity.jdnd.course3.critter.dao.entity;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "skills")
public class SkillsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long skillsId;

    @ManyToOne
    EmployeeEntity employee;

    @Enumerated(EnumType.STRING)
    EmployeeSkill skill;
}
