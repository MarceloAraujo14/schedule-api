package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.entity.ScheduleEntity;
import com.schedule.service.interfaces.FindAllSchedulesByDateAndAttendantIdService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
@Component
public class FindAllSchedulesByDateAndAttendantIdServiceImpl implements FindAllSchedulesByDateAndAttendantIdService {

    private final ScheduleRepository repository;

    @Override
    public List<Schedule> execute(String date, String attendantId) {
        log.info("M execute, date={}, attendantId={}", date, attendantId);
        return repository.findAllByDateAndAttendantId(
                LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                UUID.fromString(attendantId)).stream().map(ScheduleEntity::getSchedule).collect(Collectors.toList());
    }
}
