package com.udacity.jdnd.course3.critter.dao.repository;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.dao.entity.DaysAvailableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface DaysAvailableRepository extends JpaRepository<DaysAvailableEntity, Long> {
    List<DaysAvailableEntity> findAllByDayOfWeek(DayOfWeek dayOfWeek);

    DaysAvailableEntity findByEmployeeEmployeeIdAndDayOfWeek(long employeeId, DayOfWeek dayOfWeek);
}

