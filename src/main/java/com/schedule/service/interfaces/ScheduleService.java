package com.schedule.service.interfaces;

import com.schedule.model.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule save(Schedule schedule);
    Schedule updateById(String scheduleId);
    Schedule findById(String scheduleId);
    List<Schedule> findAllByDate(String date);
    void deleteById(String scheduleId);

}
