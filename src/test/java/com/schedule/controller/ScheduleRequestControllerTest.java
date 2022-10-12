package com.schedule.controller;

import com.google.gson.Gson;
import com.schedule.model.Schedule;
import com.schedule.service.ScheduleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest
class ScheduleRequestControllerTest {

    @MockBean
    ScheduleServiceImpl service;
    @Captor
    ArgumentCaptor<Schedule> scheduleCaptor;

    @Autowired
    MockMvc mockMvc;

    @Test
    void should_save_schedule_success() throws Exception {
        Schedule schedule = getSaveModel();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(schedule);
        when(service.save(schedule)).thenReturn(schedule);
        mockMvc.perform(post("/api/schedule").contentType(MediaType.APPLICATION_JSON).content(jsonStr))
                .andExpect(status().isOk());

        verify(service, times(1)).save(scheduleCaptor.capture());
    }

    @Test
    void should_getAllByDate_schedule_success() throws Exception {
        Schedule schedule = getSaveModel();

        when(service.findAllByDateAndAttendantId(schedule.getDate(), schedule.getAttendantId())).thenReturn(List.of(schedule));
        String url = String.format("/api/schedule?date=%s&attendantId=%s", schedule.getDate(), schedule.getAttendantId());
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].scheduleId").value(schedule.getScheduleId()));

        verify(service, times(1)).findAllByDateAndAttendantId(schedule.getDate(), schedule.getAttendantId());
    }

    @Test
    void should_getById_schedule_success() throws Exception {
        Schedule schedule = getSaveModel();

        when(service.findById(schedule.getScheduleId())).thenReturn(schedule);
        mockMvc.perform(get("/api/schedule/"+schedule.getScheduleId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value(schedule.getDate()));

        verify(service, times(1)).findById(schedule.getScheduleId());
    }

    @Test
    void should_updateById_schedule_success() throws Exception {
        Schedule schedule = getSaveModel();
        Schedule scheduleToUpdate = getUpdatedModel();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(scheduleToUpdate);

        when(service.update(scheduleToUpdate)).thenReturn(scheduleToUpdate);
        mockMvc.perform(put("/api/schedule/").contentType(MediaType.APPLICATION_JSON).content(jsonStr))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value(scheduleToUpdate.getDate()));

        verify(service, times(1)).update(scheduleToUpdate);
    }

    @Test
    void should_deleteById_schedule_success() throws Exception {
        Schedule schedule = getSaveModel();

        mockMvc.perform(delete("/api/schedule/"+schedule.getScheduleId()))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteById(schedule.getScheduleId());
    }

    private Schedule getSaveModel(){
        return Schedule.builder()
                .scheduleId(UUID.randomUUID().toString())
                .date(LocalDate.now().plusWeeks(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")))
                .endAt(LocalTime.now().plusMinutes(15).format(DateTimeFormatter.ofPattern("HH:mm")))
                .attendantId(UUID.randomUUID().toString())
                .description("Descritption")
                .build();
    }

    private Schedule getUpdatedModel(){
        return Schedule.builder()
                .scheduleId(UUID.randomUUID().toString())
                .date(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(LocalTime.of(10,0).format(DateTimeFormatter.ofPattern("HH:mm")))
                .endAt(LocalTime.of(10,30).format(DateTimeFormatter.ofPattern("HH:mm")))
                .attendantId(UUID.randomUUID().toString())
                .description("Descritption")
                .build();
    }

}