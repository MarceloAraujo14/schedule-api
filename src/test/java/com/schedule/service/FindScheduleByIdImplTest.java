package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.entity.ScheduleEntity;
import com.schedule.service.exception.ScheduleNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindScheduleByIdImplTest {

    @InjectMocks
    FindScheduleByIdImpl underTest;
    @Mock
    ScheduleRepository repository;

    @Test
    void should_findById_success() throws ScheduleNotFoundException {
        Schedule schedule = getSchedule();
        ScheduleEntity entity = schedule.getEntity();
        when(repository.findById(entity.getScheduleId())).thenReturn(Optional.of(entity));
        underTest.execute(schedule.getScheduleId());
        verify(repository, times(1)).findById(entity.getScheduleId());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_when_ScheduleNotFound(){
        Schedule schedule = getSchedule();
        ScheduleEntity entity = schedule.getEntity();
        when(repository.findById(entity.getScheduleId())).thenReturn(Optional.empty());
        assertThrows(ScheduleNotFoundException.class, () -> underTest.execute(schedule.getScheduleId()));
        verify(repository, times(1)).findById(entity.getScheduleId());
        verifyNoMoreInteractions(repository);
    }

    private Schedule getSchedule(){
        return Schedule.builder()
                .scheduleId(UUID.randomUUID().toString())
                .date(LocalDate.of(2022,10,9).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(LocalTime.of(10,0).format(DateTimeFormatter.ofPattern("HH:mm")))
                .endAt(LocalTime.of(10,30).format(DateTimeFormatter.ofPattern("HH:mm")))
                .attendantId(UUID.randomUUID().toString())
                .description("Descritption")
                .build();
    }

}