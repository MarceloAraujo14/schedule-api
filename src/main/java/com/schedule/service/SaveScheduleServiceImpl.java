package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.entity.ScheduleEntity;
import com.schedule.service.exception.ScheduleBeforeNowException;
import com.schedule.service.exception.ScheduleOverlapTimeException;
import com.schedule.service.interfaces.SaveScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class SaveScheduleServiceImpl implements SaveScheduleService {

    private final ScheduleRepository repository;

    @Override
    public Schedule execute(Schedule schedule) {

        ScheduleEntity entity = schedule.getEntity();
        validateOverlapTime(entity);
        validateScheduleBeforeNow(entity);

        entity.setCreatedAt(Objects.nonNull(entity.getCreatedAt()) ? entity.getCreatedAt() : LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        repository.save(entity);
        return schedule;
    }

    private void validateOverlapTime(ScheduleEntity entity){
        List<ScheduleEntity> entities = repository.findAllByDateAndAttendantId(entity.getDate(), entity.getAttendantId());
        entities.forEach(schedule -> {
            if (schedule.getStartAt().isBefore(entity.getEndAt()) || schedule.getEndAt().isAfter(entity.getStartAt())){
                throw new ScheduleOverlapTimeException();
            }
        });
    }

    private void validateScheduleBeforeNow(ScheduleEntity entity){
        if ((entity.getDate().isEqual(LocalDate.now()) || entity.getDate().isBefore(LocalDate.now()))
                && entity.getStartAt().isBefore(LocalTime.now())){
            throw new ScheduleBeforeNowException();
        }
    }
}
