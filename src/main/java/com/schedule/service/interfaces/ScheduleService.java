package com.schedule.service.interfaces;

import com.schedule.model.Schedule;
import com.schedule.service.exception.ScheduleBeforeNowException;
import com.schedule.service.exception.ScheduleNotFoundException;

import java.util.List;

public interface ScheduleService {
    Schedule save(Schedule schedule) throws ScheduleBeforeNowException;
    Schedule update(Schedule schedule) throws ScheduleNotFoundException, ScheduleBeforeNowException;
    Schedule findById(String scheduleId) throws ScheduleNotFoundException;
    List<Schedule> findAllByDateAndAttendantId(String date, String attendantId);
    void deleteById(String scheduleId);

}
