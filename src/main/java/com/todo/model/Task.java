package com.todo.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import java.util.stream.IntStream;

public class Task {

    private String id;
    private String description;
    private LocalDate dueDate;
    private Status status;
    private LocalDate creationDate;

    public Task(String description, LocalDate dueDate) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.dueDate = dueDate;
        this.status = Status.NOT_STARTED;
        this.creationDate = LocalDate.now();
    }

    public Task() {}

    public String getId() {
        return id;
    }

    public void setId(String newId) {
        this.id = newId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate newDueDate) {
        this.dueDate = newDueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
