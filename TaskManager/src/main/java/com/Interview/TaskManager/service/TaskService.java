package com.Interview.TaskManager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Interview.TaskManager.exception.BadRequestException;
import com.Interview.TaskManager.exception.TaskNotFoundException;
import com.Interview.TaskManager.repository.TaskRepository;
import com.Interview.TaskManager.task.Task;
import com.Interview.TaskManager.task.TaskStatus;

@Service
public class TaskService {

	private final TaskRepository repo;

	public TaskService(TaskRepository repo) {
		this.repo = repo;
	}

	public Task create(String title, String desc, TaskStatus status, LocalDate due) {
		if (title == null || title.isBlank())
			throw new IllegalArgumentException("title required");
		if (due == null || !due.isAfter(LocalDate.now()))
			throw new BadRequestException("due_date must be in the future");
		return repo.save(new Task(title, desc, status, due));
	}

	public Task get(String id) {
		return repo.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found: " + id));
	}

	public List<Task> list(TaskStatus status, int page, int size) {
		if (page < 1 || size < 1) {
			throw new BadRequestException("page and size must be greater than 0");
		}

		List<Task> all = repo.findAll(status);

		int from = (page - 1) * size;
		if (from >= all.size())
			return List.of();

		int to = Math.min(from + size, all.size());
		return all.subList(from, to);
	}

	public void delete(String id) {
		repo.deleteById(id);
	}

	public Task update(String id, String title, String description, TaskStatus status, LocalDate dueDate) {

		Task task = repo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

		if (dueDate != null && !dueDate.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("due_date must be in the future");
		}

		task.update(title, description, status, dueDate);
		return repo.save(task);
	}
}