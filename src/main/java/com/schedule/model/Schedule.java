package com.schedule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schedule.repository.entity.ScheduleEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Schedule {

    @NotBlank
    private String scheduleId;
    @NotBlank
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String date;
    @NotBlank
    @DateTimeFormat(pattern = "HH:mm")
    private String startAt;
    @NotBlank
    @DateTimeFormat(pattern = "HH:mm")
    private String endAt;
    @NotBlank
    private String attendantId;
    @NotBlank
    private String description;
    @JsonIgnore
    @ToString.Exclude
    private String createdAt;
    @JsonIgnore
    @ToString.Exclude
    private String updatedAt;

    @JsonIgnore
    public ScheduleEntity getEntity(){
        return ScheduleEntity.builder()
                .scheduleId(UUID.fromString(scheduleId))
                .date(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(LocalTime.parse(startAt, DateTimeFormatter.ofPattern("HH:mm")))
                .endAt(LocalTime.parse(endAt, DateTimeFormatter.ofPattern("HH:mm")))
                .attendantId(UUID.fromString(attendantId))
                .description(description)
                .createdAt(Objects.nonNull(createdAt) ? LocalDateTime.parse(createdAt) : LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
