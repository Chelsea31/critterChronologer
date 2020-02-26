package com.udacity.jdnd.course3.critter.dao.entity;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.model.user.EmployeeSkill;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "schedule")
@Data
@Builder
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    GeneratedSequence generatedSequence;
    private Long employeeId;
    private Long petId;
    private LocalDate date;
    private EmployeeSkill activity;
}
