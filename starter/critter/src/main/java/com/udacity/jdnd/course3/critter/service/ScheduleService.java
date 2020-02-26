package com.udacity.jdnd.course3.critter.service;

/*
 * @author Shubham Bansal
 */

import com.udacity.jdnd.course3.critter.dao.ScheduleDao;
import com.udacity.jdnd.course3.critter.dao.entity.GeneratedSequence;
import com.udacity.jdnd.course3.critter.dao.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.model.pet.PetDTO;
import com.udacity.jdnd.course3.critter.model.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.user.EmployeeSkill;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.ComparableComparator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    ScheduleDao scheduleDao;

    @Autowired
    PetService petService;

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        GeneratedSequence generatedSequence = new GeneratedSequence();
        scheduleDTO.getEmployeeIds().forEach(employeeId -> {
            scheduleDTO.getPetIds().forEach(petId -> {
                scheduleDTO.getActivities().forEach(activity -> {
                    ScheduleEntity scheduleEntity = createScheduleEntity(generatedSequence, employeeId, petId, activity, scheduleDTO.getDate());
                    scheduleDao.addSchedule(scheduleEntity);
                });
            });
        });
        return scheduleDTO;
    }

    private ScheduleEntity createScheduleEntity(GeneratedSequence sequence, long employeeId, long petId, EmployeeSkill activity, LocalDate date) {
        ScheduleEntity scheduleEntity = ScheduleEntity
                .builder()
                .activity(activity)
                .date(date)
                .employeeId(employeeId)
                .petId(petId)
                .build();

        scheduleEntity.setGeneratedSequence(sequence);
        return scheduleEntity;
    }

    public List<ScheduleDTO> getScheduleForPet(long petId) {
        List<ScheduleEntity> scheduleEntitiesByPetId = scheduleDao.getScheduleByPetId(petId);
        scheduleEntitiesByPetId = getScheduleEntitiesByGeneratedSequence(scheduleEntitiesByPetId);
        return Lists.newArrayList(mapScheduleEntityToDto(scheduleEntitiesByPetId));
    }

    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        List<ScheduleEntity> scheduleByEmployeeId = scheduleDao.getScheduleByEmployeeId(employeeId);
        scheduleByEmployeeId.get(0).getGeneratedSequence().getId();
        scheduleByEmployeeId = getScheduleEntitiesByGeneratedSequence(scheduleByEmployeeId);
        return Lists.newArrayList(mapScheduleEntityToDto(scheduleByEmployeeId));
    }

    List<ScheduleEntity> getScheduleEntitiesByGeneratedSequence(List<ScheduleEntity> scheduleEntities) {
        scheduleDao.findByGeneratedSequenceId(scheduleEntities.get(0).getGeneratedSequence().getId()).stream()
                .map(scheduleEntities::add).collect(Collectors.toList());
        return new ArrayList<>(new HashSet<>(scheduleEntities));
    }

    ScheduleDTO mapScheduleEntityToDto(List<ScheduleEntity> scheduleEntities) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDate(scheduleEntities.get(0).getDate());
        scheduleDTO.setActivities(scheduleEntities.stream().map(ScheduleEntity::getActivity).collect(Collectors.toSet()));
        scheduleDTO.setEmployeeIds(new ArrayList<>(scheduleEntities.stream().map(ScheduleEntity::getEmployeeId).collect(Collectors.toSet())));
        scheduleDTO.getEmployeeIds().sort(new ComparableComparator<>());
        scheduleDTO.setPetIds(new ArrayList<>(scheduleEntities.stream().map(ScheduleEntity::getPetId).collect(Collectors.toSet())));
        return scheduleDTO;
    }

    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {
        List<PetDTO> petsByOwner = petService.getPetsByOwner(customerId);
        List<ScheduleEntity> scheduleEntityList = new ArrayList<>();
        petsByOwner.stream().map(petDTO -> scheduleDao.getScheduleByPetId(petDTO.getId())).peek(scheduleEntities -> scheduleEntityList.addAll(scheduleEntities)).collect(Collectors.toSet());
        return Lists.newArrayList(mapScheduleEntityToDto(scheduleEntityList));
    }
}
