package com.udacity.jdnd.course3.critter.dao.entity;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
@Data
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Enumerated(EnumType.STRING)
    PetType type;
    String name;
    long ownerId;
    LocalDate birthDate;
    String notes;
}
