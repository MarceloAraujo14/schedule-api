package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.service.exception.ScheduleBeforeNowException;
import com.schedule.service.exception.ScheduleNotFoundException;
import com.schedule.service.interfaces.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
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
        log.info("M save, request={}", schedule);
        return saveScheduleService.execute(schedule);
    }

    @Override
    public Schedule update(Schedule schedule) throws ScheduleNotFoundException, ScheduleBeforeNowException {
        log.info("M update, request={}", schedule);
        return updateScheduleById.execute(schedule);
    }

    @Override
    public Schedule findById(String scheduleId) throws ScheduleNotFoundException {
        log.info("M findById, scheduleId={}", scheduleId);
        return findScheduleById.execute(scheduleId);
    }

    @Override
    public List<Schedule> findAllByDateAndAttendantId(String date, String attendantId) {
        log.info("M findAllByDateAndAttendantId, date={}, attendantId={}", date, attendantId);
        return findAllSchedulesByDateAndAttendantIdService.execute(date, attendantId);
    }

    @Override
    public void deleteById(String scheduleId) {
        log.info("M deleteById, scheduleId={}", scheduleId);
        deleteScheduleById.execute(scheduleId);
    }

}
