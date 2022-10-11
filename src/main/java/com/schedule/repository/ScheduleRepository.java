package com.schedule.repository;

import com.schedule.repository.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, UUID> {
    List<ScheduleEntity> findAllByDate(LocalDate date);
}
