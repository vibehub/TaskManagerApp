package com.Interview.TaskManager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.Interview.TaskManager.exception.BadRequestException;
import com.Interview.TaskManager.exception.TaskNotFoundException;
import com.Interview.TaskManager.repository.TaskRepository;
import com.Interview.TaskManager.task.Task;
import com.Interview.TaskManager.task.TaskStatus;

class TaskServiceTest {

    TaskRepository repo = mock(TaskRepository.class);
    TaskService service = new TaskService(repo);

    @Test
    void shouldCreateTask() {
        Task task = new Task("T", "D", TaskStatus.PENDING, LocalDate.now().plusDays(1));
        when(repo.save(any())).thenReturn(task);

        Task result = service.create("T", "D", TaskStatus.PENDING, LocalDate.now().plusDays(1));

        assertNotNull(result);
        verify(repo).save(any());
    }

    @Test
    void shouldThrowIfDueDateInPast() {
        assertThrows(BadRequestException.class, () ->
                service.create("T", null, null, LocalDate.now().minusDays(1))
        );
    }

    @Test
    void shouldPaginateResults() {
        when(repo.findAll(null)).thenReturn(List.of(
                mock(Task.class),
                mock(Task.class),
                mock(Task.class)
        ));

        List<Task> page = service.list(null, 1, 2);

        assertEquals(2, page.size());
    }

    @Test
    void shouldThrowIfTaskNotFound() {
        when(repo.findById("x")).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () ->
                service.get("x")
        );
    }
}