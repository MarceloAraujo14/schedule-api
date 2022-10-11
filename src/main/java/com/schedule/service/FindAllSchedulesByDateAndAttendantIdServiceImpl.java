package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.entity.ScheduleEntity;
import com.schedule.service.interfaces.FindAllSchedulesByDateAndAttendantIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class FindAllSchedulesByDateAndAttendantIdServiceImpl implements FindAllSchedulesByDateAndAttendantIdService {

    private final ScheduleRepository repository;

    @Override
    public List<Schedule> execute(String date, String attendantId) {
        return repository.findAllByDateAndAttendantId(
                LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                UUID.fromString(attendantId)).stream().map(ScheduleEntity::getSchedule).collect(Collectors.toList());
    }
}
