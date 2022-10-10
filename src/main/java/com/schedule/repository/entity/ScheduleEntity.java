package com.schedule.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@Entity
@Table(name = "schedule_tb")
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntity {
    @Id
    private UUID scheduleId;
    private LocalDate date;
    private LocalTime startAt;
    private LocalTime endAt;
    private UUID attendantId;
    private String description;
}
