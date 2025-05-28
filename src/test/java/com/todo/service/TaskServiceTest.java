package com.todo.service;

import com.todo.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class TaskServiceTest {

    private List<Task> tasks = new ArrayList<>();
    private TaskService service;

    @BeforeEach
    void SetUp() {
        Task task1 = new Task("test1", LocalDate.now());
        Task task2 = new Task("test2", LocalDate.parse("2030-05-28"));
        tasks.add(task1);
        tasks.add(task2);

        service = new TaskService(tasks);
    }

    @Test
    void getAllTest() {
        List<Task> retrievedTasks = service.getAll();

        for (int i = 0; i < retrievedTasks.size(); i++) {
            assertEquals(tasks.get(i), retrievedTasks.get(i));
        }
    }

}