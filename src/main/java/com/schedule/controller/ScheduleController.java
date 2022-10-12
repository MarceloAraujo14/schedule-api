package com.schedule.controller;

import com.schedule.model.Schedule;
import com.schedule.service.ScheduleServiceImpl;
import com.schedule.service.exception.ScheduleBeforeNowException;
import com.schedule.service.exception.ScheduleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleServiceImpl scheduleServiceImpl;

    @PostMapping
    public ResponseEntity create(@RequestBody Schedule schedule) throws ScheduleBeforeNowException {
        return ResponseEntity.ok(scheduleServiceImpl.save(schedule));
    }

    @GetMapping
    public ResponseEntity findAllByDateAndAttendantId(@RequestParam("date") String date, @RequestParam("attendantId") String attendantId){
        return ResponseEntity.ok(scheduleServiceImpl.findAllByDateAndAttendantId(date, attendantId));
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity findById(@PathVariable("scheduleId") String scheduleId) throws ScheduleNotFoundException {
        return ResponseEntity.ok(scheduleServiceImpl.findById(scheduleId));
    }

    @PutMapping
    public ResponseEntity updateById(@RequestBody Schedule schedule) throws ScheduleNotFoundException, ScheduleBeforeNowException {
        return ResponseEntity.ok(scheduleServiceImpl.update(schedule));
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteById(@PathVariable("scheduleId") String scheduleId){
        scheduleServiceImpl.deleteById(scheduleId);
    }
}
