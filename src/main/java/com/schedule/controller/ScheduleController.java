package com.schedule.controller;

import com.schedule.model.Schedule;
import com.schedule.service.ScheduleServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleServiceImpl scheduleServiceImpl;

    @PostMapping
    public ResponseEntity create(@RequestBody Schedule schedule){
        return ResponseEntity.ok(scheduleServiceImpl.save(schedule));
    }

    @GetMapping
    public ResponseEntity findAllByDate(@RequestParam("date") String date){
        return ResponseEntity.ok(scheduleServiceImpl.findAllByDate(date));
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity findById(@PathVariable("scheduleId") String scheduleId){
        return ResponseEntity.ok(scheduleServiceImpl.findById(scheduleId));
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity updateById(@PathVariable("scheduleId") String scheduleId){
        return ResponseEntity.ok(scheduleServiceImpl.updateById(scheduleId));
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteById(@PathVariable("scheduleId") String scheduleId){
        scheduleServiceImpl.deleteById(scheduleId);
    }
}
