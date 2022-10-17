package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.service.exception.ScheduleBeforeNowException;
import com.schedule.service.exception.ScheduleNotFoundException;
import com.schedule.service.interfaces.UpdateScheduleById;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Log4j2
@Component
@AllArgsConstructor
public class UpdateScheduleByIdImpl implements UpdateScheduleById {

    private final FindScheduleByIdImpl findScheduleById;
    private final SaveScheduleServiceImpl saveScheduleService;

    @Override
    public Schedule execute(Schedule schedule) throws ScheduleNotFoundException, ScheduleBeforeNowException {
        log.info("M execute, request={}", schedule);
        Schedule updatable = findScheduleById.execute(schedule.getScheduleId());

        updatable.setDate(Objects.nonNull(schedule.getDate())? schedule.getDate() : updatable.getDate());
        updatable.setStartAt(Objects.nonNull(schedule.getStartAt())? schedule.getStartAt() : updatable.getStartAt());
        updatable.setEndAt(Objects.nonNull(schedule.getEndAt())? schedule.getEndAt() : updatable.getEndAt());
        updatable.setAttendantId(Objects.nonNull(schedule.getAttendantId())? schedule.getAttendantId() : updatable.getAttendantId());
        updatable.setDescription(Objects.nonNull(schedule.getDescription())? schedule.getDescription() : updatable.getDescription());
        updatable.setCreatedAt(schedule.getCreatedAt());

        return saveScheduleService.execute(updatable);
    }
}
