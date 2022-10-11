package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.repository.ScheduleRepository;
import com.schedule.repository.entity.ScheduleEntity;
import com.schedule.service.exception.ScheduleOverlapTimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveScheduleServiceImplTest {

    @InjectMocks
    SaveScheduleServiceImpl underTest;

    @Mock
    ScheduleRepository repository;
    @Captor
    ArgumentCaptor<ScheduleEntity> entityCaptor;

    @Test
    void should_save_schedule_success(){
        Schedule schedule = getSchedule();
        ScheduleEntity entity = schedule.getEntity();
        when(repository.save(entity)).thenReturn(entity);
        underTest.execute(schedule);
        verify(repository, times(1)).save(entityCaptor.capture());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_when_scheduleOverlapTime(){
        Schedule schedule = getSchedule();
        ScheduleEntity entity = schedule.getEntity();
        when(repository.findAllByDate(entity.getDate())).thenReturn(List.of(getScheduleOverlap().getEntity()));
        assertThrows(ScheduleOverlapTimeException.class, () -> underTest.execute(schedule));
        verify(repository, times(1)).findAllByDate(entity.getDate());
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

    private Schedule getScheduleOverlap(){
        return Schedule.builder()
                .scheduleId(UUID.randomUUID().toString())
                .date(LocalDate.of(2022,10,9).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(LocalTime.of(10,0).format(DateTimeFormatter.ofPattern("HH:mm")))
                .endAt(LocalTime.of(10,15).format(DateTimeFormatter.ofPattern("HH:mm")))
                .attendantId(UUID.randomUUID().toString())
                .description("Descritption")
                .build();
    }

}