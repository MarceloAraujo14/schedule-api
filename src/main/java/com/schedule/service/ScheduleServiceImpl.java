package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.service.interfaces.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Override
    public Schedule save(Schedule schedule) {
        return null;
    }

    @Override
    public Schedule updateById(String scheduleId) {
        return null;
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
