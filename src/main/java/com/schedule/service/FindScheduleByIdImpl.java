package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.repository.ScheduleRepository;
import com.schedule.service.exception.ScheduleNotFoundException;
import com.schedule.service.interfaces.FindScheduleById;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Log4j2
@Component
@AllArgsConstructor
public class FindScheduleByIdImpl implements FindScheduleById {

    private final ScheduleRepository repository;

    @Override
    public Schedule execute(String scheduleId) throws ScheduleNotFoundException {
        log.info("M execute, scheduleId={}", scheduleId);
        return repository.findById(UUID.fromString(scheduleId)).orElseThrow(ScheduleNotFoundException::new).getSchedule();
    }
}
