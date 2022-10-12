package com.schedule.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schedule.model.Schedule;
import com.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application.yml")
@ActiveProfiles(value = "test")
class SaveScheduleTest {

    @Value("${server.port}")
    String serverPort;

    @Autowired
    private ScheduleRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test(){
       assertEquals("8082", serverPort);
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void should_save_schedule_success() throws Exception {
        String jsonSchedule = ObjecttoJson(getSchedule());
        System.out.println(jsonSchedule);
        ResultActions scheduleResults = mockMvc.perform(post("/api/schedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(jsonSchedule)));

        scheduleResults.andExpect(status().isOk())
        .andExpect(jsonPath("$.scheduleId").value(getSchedule().getScheduleId()));

        assertThat(repository.findById(UUID.fromString(getSchedule().getScheduleId())))
                .isPresent();
    }

    @Test
    void should_return_400_when_schedule_same_time() throws Exception {
        String jsonSchedule = ObjecttoJson(getSchedule());
        System.out.println(jsonSchedule);
        ResultActions scheduleResults = mockMvc.perform(post("/api/schedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(jsonSchedule)));

        scheduleResults.andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(getSchedule().getScheduleId()));

        assertThat(repository.findById(UUID.fromString(getSchedule().getScheduleId())))
                .isPresent();

        ResultActions sameTimeResult = mockMvc.perform(post("/api/schedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(jsonSchedule)));

        sameTimeResult.andExpect(status().isBadRequest())
                .andExpect(content().string("The chosen time is already booked."));


    }

    Schedule getSchedule() {
        return Schedule.builder()
                .scheduleId("f20c6013-724e-4c36-94fd-7504a5c53b02")
                .date(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .startAt(LocalTime.now().plusMinutes(15).format(DateTimeFormatter.ofPattern("HH:mm")))
                .endAt(LocalTime.now().plusMinutes(30).format(DateTimeFormatter.ofPattern("HH:mm")))
                .attendantId("4418a3af-9008-438f-a17c-ef0fe91d272c")
                .description("Testing save schedule")
                .build();
    }

    private String ObjecttoJson(Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }

}
