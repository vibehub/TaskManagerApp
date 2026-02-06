package com.Interview.TaskManager.dto;

import com.Interview.TaskManager.task.TaskStatus;

import java.time.LocalDate;

public class TaskResponse {

	private String id;
	private String title;
	private String description;
	private TaskStatus status;
	private LocalDate due_date;

	public TaskResponse(String id, String title, String description, TaskStatus status, LocalDate due_date) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.due_date = due_date;
	}

	// getters only (immutable response)
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

	public LocalDate getDue_date() {
		return due_date;
	}
}