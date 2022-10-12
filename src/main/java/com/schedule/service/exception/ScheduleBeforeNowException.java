package com.schedule.service.exception;

public class ScheduleBeforeNowException extends Exception{

    private static final String MESSAGE = "It is not possible to book a time earlier than the current one";

    public ScheduleBeforeNowException() {
        super(MESSAGE);
    }
}
