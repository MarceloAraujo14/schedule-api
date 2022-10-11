package com.schedule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schedule.repository.entity.ScheduleEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Schedule {

    private String scheduleId;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String date;
    @DateTimeFormat(pattern = "HH:mm")
    private String startAt;
    @DateTimeFormat(pattern = "HH:mm")
    private String endAt;
    private String attendantId;
    private String description;
    @JsonIgnore
    @ToString.Exclude
    private String createdAt;
    @JsonIgnore
    @ToString.Exclude
    private String updatedAt;

    public ScheduleEntity getEntity(){
        return ScheduleEntity.builder()
                .scheduleId(UUID.fromString(scheduleId))
                .date(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(LocalTime.parse(startAt, DateTimeFormatter.ofPattern("HH:mm")))
                .endAt(LocalTime.parse(endAt, DateTimeFormatter.ofPattern("HH:mm")))
                .attendantId(UUID.fromString(attendantId))
                .description(description)
                .createdAt(LocalDateTime.parse(createdAt))
                .updatedAt(LocalDateTime.parse(updatedAt))
                .build();
    }
}
