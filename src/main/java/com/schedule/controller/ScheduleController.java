package com.schedule.controller;

import com.schedule.model.Schedule;
import com.schedule.service.ScheduleServiceImpl;
import com.schedule.service.exception.ScheduleBeforeNowException;
import com.schedule.service.exception.ScheduleNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleServiceImpl scheduleServiceImpl;

    @PostMapping
    public ResponseEntity create(@RequestBody Schedule schedule) throws ScheduleBeforeNowException {
        log.info("M create, request={}", schedule);
        return ResponseEntity.ok(scheduleServiceImpl.save(schedule));
    }

    @GetMapping
    public ResponseEntity findAllByDateAndAttendantId(@RequestParam("date") String date, @RequestParam("attendantId") String attendantId){
        log.info("M findAllByDateAndAttendantId, date={}, attendantId={}", date, attendantId);
        return ResponseEntity.ok(scheduleServiceImpl.findAllByDateAndAttendantId(date, attendantId));
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity findById(@PathVariable("scheduleId") String scheduleId) throws ScheduleNotFoundException {
        log.info("M findById, scheduleID={}", scheduleId);
        return ResponseEntity.ok(scheduleServiceImpl.findById(scheduleId));
    }

    @PutMapping
    public ResponseEntity updateById(@RequestBody Schedule schedule) throws ScheduleNotFoundException, ScheduleBeforeNowException {
        log.info("M updateById, request={}", schedule);
        return ResponseEntity.ok(scheduleServiceImpl.update(schedule));
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteById(@PathVariable("scheduleId") String scheduleId){
        log.info("M deleteById, scheduleId={}", scheduleId);
        scheduleServiceImpl.deleteById(scheduleId);
    }
}
