package com.todo.ui;

import com.todo.model.*;
import com.todo.service.StorageService;
import com.todo.service.TaskService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * shows menu, reads user input, uses task service
 */
public class ConsoleUI {
    private TaskService taskService;
    private StorageService storageService;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleUI(TaskService taskService, StorageService storageService) {
        this.taskService = taskService;
        this.storageService = storageService;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n1) List tasks\n2) Add task\n3) Mark task done\n4) Delete task\n0) Exit");
            System.out.print("Select: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": listTasks(); break;
                case "2": addTask(); break;
                case "3": markDone(); break;
                case "4": deleteTask(); break;
                case "0": running = false; break;
                default: System.out.println("Invalid choice.");
            }
        }
        storageService.saveTasks(taskService.getAll());
        System.out.println("Goodbye!");
    }

    private void listTasks() {
        List<Task> tasks = taskService.getAll();
        if (tasks.isEmpty()) {
            System.out.println("No tasks.");
        } else {
            tasks.forEach(t -> System.out.printf("%s. [%s] %s (due: %s)%n",
                    t.getId(), t.getStatus(), t.getDescription(),
                    t.getDueDate() != null ? t.getDueDate() : "none"));
        }
    }

    private void addTask() {
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Due date (YYYY-MM-DD, empty for none): ");
        String date = scanner.nextLine();
        LocalDate due = date.isEmpty() ? null : LocalDate.parse(date);
        Task task = new Task(desc, due);
        taskService.add(task);
        System.out.println("Added.");
    }

    private void markDone() {
        System.out.print("Task ID: ");
        String id = scanner.nextLine();
        taskService.markDone(id);
        System.out.println("Marked done.");
    }

    private void deleteTask() {
        System.out.print("Task ID: ");
        String id = scanner.nextLine();
        taskService.delete(id);
        System.out.println("Deleted.");
    }
}
