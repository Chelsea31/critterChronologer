package com.udacity.jdnd.course3.critter.dao;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.dao.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.dao.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleDao {

    @Autowired
    ScheduleRepository scheduleRepository;

    public long addSchedule(ScheduleEntity scheduleEntity) {
        return scheduleRepository.save(scheduleEntity).getId();
    }

    public List<ScheduleEntity> getScheduleByEmployeeId(long employeeId) {
        return scheduleRepository.findByEmployeeId(employeeId);
    }

    public List<ScheduleEntity> getScheduleByPetId(long petId) {
        return scheduleRepository.findByPetId(petId);
    }

    public List<ScheduleEntity> findByGeneratedSequenceId(long id) {
        return scheduleRepository.findByGeneratedSequenceId(id);
    }

//    public List<Long> findAllScheduleId(Long id){
//        scheduleNumberRepository.findById(id);
//    }
}
