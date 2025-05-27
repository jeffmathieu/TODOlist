package com.todo.service;

import com.todo.model.Status;
import com.todo.model.Task;

import java.util.List;
import java.util.stream.Collectors;

/**
 * add, edit, delete, list, filter tasks
 */
public class TaskService {

    private List<Task> tasks;

    public TaskService(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getAll() {
        return tasks;
    }

    public List<Task> getPending() {
        return tasks.stream()
                .filter(t -> t.getStatus() == Status.PENDING)
                .collect(Collectors.toList());
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(String id) {
        tasks.removeIf(t -> t.getId().equals(id));
    }

    public void markDone(String id) {
        tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresent(t -> t.setStatus(Status.DONE));
    }
}
