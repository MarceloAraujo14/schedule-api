package com.schedule.service;

import com.schedule.repository.ScheduleRepository;
import com.schedule.service.interfaces.DeleteScheduleById;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Log4j2
@Component
@AllArgsConstructor
public class DeleteScheduleByIdImpl implements DeleteScheduleById {

    private final ScheduleRepository scheduleRepository;

    @Override
    public void execute(String id) {
        log.info("M execute, scheduleId={}", id);
        scheduleRepository.deleteById(UUID.fromString(id));
    }
}
