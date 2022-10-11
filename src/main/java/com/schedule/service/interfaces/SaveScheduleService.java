package com.schedule.service.interfaces;

import com.schedule.model.Schedule;
import com.schedule.service.exception.ScheduleBeforeNowException;

public interface SaveScheduleService {

    Schedule execute(Schedule schedule) throws ScheduleBeforeNowException;

}
