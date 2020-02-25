package com.udacity.jdnd.course3.critter.dao.repository;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.dao.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetsRepository extends JpaRepository<PetEntity, Long> {
    List<PetEntity> findAllByOwnerId(long ownerId);
}
