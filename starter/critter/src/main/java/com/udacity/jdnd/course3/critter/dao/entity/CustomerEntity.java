package com.udacity.jdnd.course3.critter.dao.entity;

/*
 * @author Shubham Bansal
 */

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    String phoneNumber;
    String notes;
}
