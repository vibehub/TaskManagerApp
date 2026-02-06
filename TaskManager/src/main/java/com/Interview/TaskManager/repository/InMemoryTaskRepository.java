package com.Interview.TaskManager.repository;

import com.Interview.TaskManager.task.Task;
import com.Interview.TaskManager.task.TaskStatus;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

	private final Map<String, Task> store = new ConcurrentHashMap<>();

	public Task save(Task task) {
		store.put(task.getId(), task);
		return task;
	}

	public Optional<Task> findById(String id) {
		return Optional.ofNullable(store.get(id));
	}

	public void deleteById(String id) {
		store.remove(id);
	}

	public List<Task> findAll(TaskStatus status) {
		return store.values().stream().filter(t -> status == null || t.getStatus() == status)
				.sorted(Comparator.comparing(Task::getDueDate)).toList();
	}
}