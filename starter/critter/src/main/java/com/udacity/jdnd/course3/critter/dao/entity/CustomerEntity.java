package com.udacity.jdnd.course3.critter.dao.entity;

/*
 * @author Shubham Bansal
 */

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    @Column(name = "phone_number")
    Long phoneNumber;
    String notes;
}
