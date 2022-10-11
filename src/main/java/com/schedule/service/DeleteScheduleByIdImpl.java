package com.schedule.service;

import com.schedule.repository.ScheduleRepository;
import com.schedule.service.interfaces.DeleteScheduleById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class DeleteScheduleByIdImpl implements DeleteScheduleById {

    private final ScheduleRepository scheduleRepository;

    @Override
    public void execute(String id) {
        scheduleRepository.deleteById(UUID.fromString(id));
    }
}
