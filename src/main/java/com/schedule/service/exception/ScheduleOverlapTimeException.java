package com.schedule.service.exception;

public class ScheduleOverlapTimeException extends RuntimeException{

    private static final String MESSAGE = "The chosen time is already booked.";

    public ScheduleOverlapTimeException() {
        super(MESSAGE);
    }
}
