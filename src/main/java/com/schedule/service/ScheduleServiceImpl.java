package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.service.exception.ScheduleBeforeNowException;
import com.schedule.service.exception.ScheduleNotFoundException;
import com.schedule.service.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final SaveScheduleService saveScheduleService;
    private final FindScheduleById findScheduleById;
    private final FindAllSchedulesByDateAndAttendantIdService findAllSchedulesByDateAndAttendantIdService;
    private final UpdateScheduleById updateScheduleById;
    private final DeleteScheduleById deleteScheduleById;

    @Override
    public Schedule save(Schedule schedule) throws ScheduleBeforeNowException {
        return saveScheduleService.execute(schedule);
    }

    @Override
    public Schedule updateById(Schedule schedule) throws ScheduleNotFoundException, ScheduleBeforeNowException {
        return updateScheduleById.execute(schedule);
    }

    @Override
    public Schedule findById(String scheduleId) {
        return null;
    }

    @Override
    public List<Schedule> findAllByDate(String date) {
        return null;
    }

    @Override
    public void deleteById(String scheduleId) {
    }

}
