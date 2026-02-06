package com.Interview.TaskManager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.Interview.TaskManager.task.Task;
import com.Interview.TaskManager.task.TaskStatus;

class TaskTest {

    @Test
    void shouldDefaultStatusToPending() {
        Task task = new Task(
                "Test",
                "Desc",
                null,
                LocalDate.now().plusDays(1)
        );

        assertEquals(TaskStatus.PENDING, task.getStatus());
    }

    @Test
    void shouldUpdateOnlyProvidedFields() {
        Task task = new Task(
                "Old",
                "Old desc",
                TaskStatus.PENDING,
                LocalDate.now().plusDays(1)
        );

        task.update("New", null, TaskStatus.DONE, null);

        assertEquals("New", task.getTitle());
        assertEquals("Old desc", task.getDescription());
        assertEquals(TaskStatus.DONE, task.getStatus());
    }
}