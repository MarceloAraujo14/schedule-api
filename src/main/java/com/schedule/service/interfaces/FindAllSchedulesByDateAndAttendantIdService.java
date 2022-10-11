package com.schedule.service.interfaces;

import com.schedule.model.Schedule;

import java.util.List;

public interface FindAllSchedulesByDateAndAttendantIdService {

    List<Schedule> execute(String date, String attendantId);
}
