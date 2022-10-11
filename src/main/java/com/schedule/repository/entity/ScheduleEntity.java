package com.schedule.repository.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Builder
@Entity
@Table(name = "schedule_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleEntity{
    @Id
    private UUID scheduleId;
    private LocalDate date;
    private LocalTime startAt;
    private LocalTime endAt;
    private UUID attendantId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ScheduleEntity that = (ScheduleEntity) o;
        return scheduleId != null && Objects.equals(scheduleId, that.scheduleId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
