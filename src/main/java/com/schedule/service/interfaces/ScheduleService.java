package com.schedule.service.interfaces;

import com.schedule.model.Schedule;
import com.schedule.service.exception.ScheduleBeforeNowException;
import com.schedule.service.exception.ScheduleNotFoundException;

import java.util.List;

public interface ScheduleService {
    Schedule save(Schedule schedule) throws ScheduleBeforeNowException;
    Schedule updateById(Schedule schedule) throws ScheduleNotFoundException, ScheduleBeforeNowException;
    Schedule findById(String scheduleId);
    List<Schedule> findAllByDate(String date);
    void deleteById(String scheduleId);

}
