package com.udacity.jdnd.course3.critter.dao.entity;

/*
 * @author Shubham Bansal
 */

import lombok.Data;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Data
@Table(name = "days_available")
public class DaysAvailableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long daysAvailableId;

    @ManyToOne
    EmployeeEntity employee;

    @Enumerated(EnumType.STRING)
    DayOfWeek dayOfWeek;
}
