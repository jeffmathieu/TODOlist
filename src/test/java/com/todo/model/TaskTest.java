package com.todo.model;

import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class TaskTest {

    @Test
    void initTest() {
        Task task = new Task("testing", LocalDate.parse("2025-05-28"));
        assertNotNull(task.getId());
        assertEquals("testing", task.getDescription());
        assertEquals(LocalDate.parse("2025-05-28"), task.getDueDate());
        assertEquals(Status.NOT_STARTED, task.getStatus());
        assertEquals(LocalDate.now(), task.getCreationDate());
    }

    @Test
    void secondInitTest() {
        Task task = new Task();

        task.setId("123");
        task.setCreationDate(LocalDate.now());
        task.setStatus(Status.DONE);
        task.setDescription("testing again");
        task.setDueDate(LocalDate.parse("2030-09-10"));

        assertEquals("123", task.getId());
        assertEquals("testing again", task.getDescription());
        assertEquals(LocalDate.parse("2030-09-10"), task.getDueDate());
        assertEquals(Status.DONE, task.getStatus());
        assertEquals(LocalDate.now(), task.getCreationDate());
    }

}