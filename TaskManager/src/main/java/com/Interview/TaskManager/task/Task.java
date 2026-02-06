package com.Interview.TaskManager.task;

import java.time.LocalDate;
import java.util.UUID;

public class Task {

	private final String id;
	private String title;
	private String description;
	private TaskStatus status;
	private LocalDate dueDate;

	public Task(String title, String description, TaskStatus status, LocalDate dueDate) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.description = description;
		this.status = status == null ? TaskStatus.PENDING : status;
		this.dueDate = dueDate;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void update(String title, String description, TaskStatus status, LocalDate dueDate) {
		if (title != null)
			this.title = title;
		if (description != null)
			this.description = description;
		if (status != null)
			this.status = status;
		if (dueDate != null)
			this.dueDate = dueDate;
	}
}