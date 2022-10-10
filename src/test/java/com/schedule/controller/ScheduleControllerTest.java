package com.schedule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.ResultActions;

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
class ScheduleControllerTest {

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
        ResultActions resultActions = mockMvc.perform(post("/api/schedule").contentType(MediaType.APPLICATION_JSON).content(jsonStr))
                .andExpect(status().isOk());

        verify(service, times(1)).save(scheduleCaptor.capture());
    }

    @Test
    void should_getAllByDate_schedule_success() throws Exception {
        Schedule schedule = getSaveModel();

        when(service.findAllByDate(schedule.getDate())).thenReturn(List.of(schedule));
        mockMvc.perform(get("/api/schedule?date="+schedule.getDate()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].scheduleId").value(schedule.getScheduleId()));

        verify(service, times(1)).findAllByDate(schedule.getDate());
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

        when(service.updateById(schedule.getScheduleId())).thenReturn(scheduleToUpdate);
        mockMvc.perform(put("/api/schedule/"+schedule.getScheduleId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value(scheduleToUpdate.getDate()));

        verify(service, times(1)).updateById(schedule.getScheduleId());
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
                .date(LocalDate.of(2022,10,9).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(LocalTime.of(10,0).format(DateTimeFormatter.ofPattern("HH:mm")))
                .endAt(LocalTime.of(10,15).format(DateTimeFormatter.ofPattern("HH:mm")))
                .attendantId(UUID.randomUUID().toString())
                .description("Descritption")
                .build();
    }

    private Schedule getUpdatedModel(){
        return Schedule.builder()
                .scheduleId(UUID.randomUUID().toString())
                .date(LocalDate.of(2022,10,14).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(LocalTime.of(10,0).format(DateTimeFormatter.ofPattern("HH:mm")))
                .endAt(LocalTime.of(10,15).format(DateTimeFormatter.ofPattern("HH:mm")))
                .attendantId(UUID.randomUUID().toString())
                .description("Descritption")
                .build();
    }

}