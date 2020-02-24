package com.udacity.jdnd.course3.critter.dao.entity;

/*
 * @author Shubham Bansal
 */

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;
    private String name;
    @OneToMany
    private Set<SkillsEntity> skills;
    @OneToMany
    private Set<DaysAvailableEntity> daysAvailable;
}
