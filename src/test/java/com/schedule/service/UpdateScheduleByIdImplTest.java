package com.schedule.service;

import com.schedule.model.Schedule;
import com.schedule.repository.ScheduleRepository;
import com.schedule.service.exception.ScheduleNotFoundException;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateScheduleByIdImplTest {

    @InjectMocks
    UpdateScheduleByIdImpl underTest;
    @Mock
    SaveScheduleServiceImpl saveScheduleService;
    @Mock
    FindScheduleByIdImpl findScheduleById;
    @Captor
    ArgumentCaptor<Schedule> scheduleCaptor;

    @Test
    void should_updateById_success() throws ScheduleNotFoundException {
        Schedule schedule = getSchedule();
        when(findScheduleById.execute(schedule.getScheduleId())).thenReturn(schedule);
        when(saveScheduleService.execute(any())).thenReturn(any());
        underTest.execute(getScheduleupdated());
        verify(findScheduleById, times(1)).execute(schedule.getScheduleId());
        verify(saveScheduleService, times(1)).execute(scheduleCaptor.capture());

        assertEquals("10-10-2022", scheduleCaptor.getValue().getDate());

    }

    private Schedule getSchedule(){
        return Schedule.builder()
                .scheduleId("6f6fffb3-e519-4f80-97b3-d88542b9c7fd")
                .date(LocalDate.of(2022,10,9).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(LocalTime.of(10,0).format(DateTimeFormatter.ofPattern("HH:mm")))
                .endAt(LocalTime.of(10,30).format(DateTimeFormatter.ofPattern("HH:mm")))
                .attendantId(UUID.randomUUID().toString())
                .description("Descritption")
                .build();
    }

    private Schedule getScheduleupdated(){
        return Schedule.builder()
                .scheduleId("6f6fffb3-e519-4f80-97b3-d88542b9c7fd")
                .date(LocalDate.of(2022,10,10).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .build();
    }
}