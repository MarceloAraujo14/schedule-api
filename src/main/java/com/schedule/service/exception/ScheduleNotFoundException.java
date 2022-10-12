package com.schedule.service.exception;

public class ScheduleNotFoundException extends Exception{

    private static final String MESSAGE = "The schedule was not found.";

    public ScheduleNotFoundException() {
        super(MESSAGE);
    }
}
