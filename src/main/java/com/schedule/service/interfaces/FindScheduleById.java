package com.schedule.service.interfaces;

import com.schedule.model.Schedule;
import com.schedule.service.exception.ScheduleNotFoundException;

public interface FindScheduleById {

    Schedule execute(String scheduleId) throws ScheduleNotFoundException;

}
