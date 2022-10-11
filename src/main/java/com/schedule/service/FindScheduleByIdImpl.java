package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.repository.ScheduleRepository;
import com.schedule.service.exception.ScheduleNotFoundException;
import com.schedule.service.interfaces.FindScheduleById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class FindScheduleByIdImpl implements FindScheduleById {

    private final ScheduleRepository repository;

    @Override
    public Schedule execute(String scheduleId) throws ScheduleNotFoundException {
        return repository.findById(UUID.fromString(scheduleId)).orElseThrow(ScheduleNotFoundException::new).getSchedule();
    }
}
