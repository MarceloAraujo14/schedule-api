package com.schedule.repository.entity;

import com.schedule.model.Schedule;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Builder
@Entity
@Table(name = "schedule")
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

    public Schedule getSchedule(){
        return Schedule.builder()
                .scheduleId(this.scheduleId.toString())
                .date(this.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(this.startAt.format(DateTimeFormatter.ofPattern("HH:mm")))
                .endAt(this.endAt.format(DateTimeFormatter.ofPattern("HH:mm")))
                .attendantId(this.attendantId.toString())
                .description(this.description)
                .createdAt(this.createdAt.toString())
                .build();
    }
}
