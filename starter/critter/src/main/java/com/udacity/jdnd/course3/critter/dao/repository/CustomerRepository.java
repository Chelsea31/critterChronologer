package com.udacity.jdnd.course3.critter.dao.repository;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.dao.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
