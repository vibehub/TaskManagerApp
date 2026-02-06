package com.Interview.TaskManager.controller;

import com.Interview.TaskManager.service.TaskService;
import com.Interview.TaskManager.task.Task;
import com.Interview.TaskManager.task.TaskStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService service;

    @Test
    void shouldCreateTask() throws Exception {
        Task task = new Task("Test", "Desc", TaskStatus.PENDING, LocalDate.now().plusDays(1));
        Mockito.when(service.create(any(), any(), any(), any())).thenReturn(task);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        { "title": "Test", "due_date": "2026-02-20" }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test"));
    }
}