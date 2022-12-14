package com.schedule.repository;

import com.schedule.repository.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, UUID> {
    List<ScheduleEntity> findAllByDate(LocalDate date);

    List<ScheduleEntity> findAllByDateAndAttendantId(LocalDate date, UUID attendantId);
}
