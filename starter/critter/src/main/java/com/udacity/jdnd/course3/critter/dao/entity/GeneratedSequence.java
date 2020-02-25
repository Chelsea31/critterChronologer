package com.udacity.jdnd.course3.critter.dao.entity;

/*
 * @author Shubham Bansal
 */

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class GeneratedSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
}
