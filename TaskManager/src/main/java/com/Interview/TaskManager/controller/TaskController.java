package com.Interview.TaskManager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Interview.TaskManager.dto.TaskResponse;
import com.Interview.TaskManager.dto.UpdateTaskRequest;
import com.Interview.TaskManager.service.TaskService;
import com.Interview.TaskManager.task.Task;
import com.Interview.TaskManager.task.TaskStatus;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService service;

	public TaskController(TaskService service) {
		this.service = service;
	}

	@PostMapping
	public Task create(@RequestBody Task req) {
		return service.create(req.getTitle(), req.getDescription(), req.getStatus(), req.getDueDate());
	}

	@GetMapping("/{id}")
	public Task get(@PathVariable String id) {
		return service.get(id);
	}

	@GetMapping
	public List<Task> list(@RequestParam(required = false ) TaskStatus status,  @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "10") int size) {
		return service.list(status, page, size);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@PutMapping("/{id}")
	public TaskResponse update(@PathVariable String id, @RequestBody UpdateTaskRequest req) {

		Task updated = service.update(id, req.getTitle(), req.getDescription(), req.getStatus(), req.getDueDate());

		return new TaskResponse(updated.getId(), updated.getTitle(), updated.getDescription(), updated.getStatus(),
				updated.getDueDate());
	}
}