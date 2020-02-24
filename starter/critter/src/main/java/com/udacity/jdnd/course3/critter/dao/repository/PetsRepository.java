package com.udacity.jdnd.course3.critter.dao.repository;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.dao.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetsRepository extends JpaRepository<PetEntity, Long> {
}
