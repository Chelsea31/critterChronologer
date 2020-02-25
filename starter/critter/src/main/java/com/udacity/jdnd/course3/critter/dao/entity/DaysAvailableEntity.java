package com.udacity.jdnd.course3.critter.dao.entity;

/*
 * @author Shubham Bansal
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Data
@Table(name = "days")
public class DaysAvailableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long daysAvailableId;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(optional = false)
    @PrimaryKeyJoinColumn(name = "employee_id")
    EmployeeEntity employee;

    @Enumerated(EnumType.STRING)
    DayOfWeek dayOfWeek;
}
