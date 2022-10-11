package com.schedule.service.interfaces;

import com.schedule.model.Schedule;
import com.schedule.service.exception.ScheduleNotFoundException;

public interface UpdateScheduleById {

    Schedule execute(Schedule schedule) throws ScheduleNotFoundException;

}
