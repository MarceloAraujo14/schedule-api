package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.entity.ScheduleEntity;
import com.schedule.service.exception.ScheduleOverlapTimeException;
import com.schedule.service.interfaces.SaveScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SaveScheduleServiceImpl implements SaveScheduleService {

    private final ScheduleRepository repository;

    @Override
    public Schedule execute(Schedule schedule) {

        ScheduleEntity entity = schedule.getEntity();
        validateOverlapTime(entity);

        repository.save(schedule.getEntity());
        return schedule;
    }

    private void validateOverlapTime(ScheduleEntity entity){
        List<ScheduleEntity> entities = repository.findAllByDate(entity.getDate());
        entities.forEach(schedule -> {
            if (schedule.getStartAt().isBefore(entity.getEndAt()) || schedule.getEndAt().isAfter(entity.getStartAt())){
                throw new ScheduleOverlapTimeException();
            }
        });

    }
}
