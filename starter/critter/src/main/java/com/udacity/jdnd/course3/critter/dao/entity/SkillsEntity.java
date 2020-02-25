package com.udacity.jdnd.course3.critter.dao.entity;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "skills")
public class SkillsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long skillsId;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(optional = false)
    @PrimaryKeyJoinColumn(name = "employee_id")
    EmployeeEntity employee;

    @Enumerated(EnumType.STRING)
    EmployeeSkill skill;
}
