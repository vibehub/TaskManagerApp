package com.Interview.TaskManager.repository;

import com.Interview.TaskManager.task.Task;
import com.Interview.TaskManager.task.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
	Task save(Task task);

	Optional<Task> findById(String id);

	void deleteById(String id);

	List<Task> findAll(TaskStatus status);
}