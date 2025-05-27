package com.todo;

import com.todo.service.StorageService;
import com.todo.service.TaskService;
import com.todo.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {
        StorageService storage = new StorageService();
        TaskService taskService = new TaskService(storage.loadTasks());
        ConsoleUI ui = new ConsoleUI(taskService, storage);
        ui.start();
    }

}
