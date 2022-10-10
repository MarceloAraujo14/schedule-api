package com.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
}
