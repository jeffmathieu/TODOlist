package com.todo.service;

import com.todo.model.Status;
import com.todo.model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * load/safe json to disk
 */
public class StorageService {
    private static final Path FILE = Paths.get("data/tasks.csv");

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        if (Files.exists(FILE)) {
            try {
                List<String> lines = Files.readAllLines(FILE);
                for (String line : lines) {
                    String[] parts = line.split("\\|");
                    if (parts.length < 5) continue;
                    Task t = new Task();
                    t.setId(parts[0]);
                    t.setDescription(parts[1]);
                    t.setDueDate(parts[2].isEmpty() ? null : LocalDate.parse(parts[2]));
                    t.setStatus(Status.valueOf(parts[3]));
                    t.setCreationDate(LocalDate.parse(parts[4]));
                    tasks.add(t);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) {
        try {
            Files.createDirectories(FILE.getParent());
            List<String> lines = tasks.stream()
                    .map(t -> String.join("|",
                            t.getId(),
                            t.getDescription().replace("|", "\\|"),
                            t.getDueDate()==null ? "" : t.getDueDate().toString(),
                            t.getStatus().name(),
                            t.getCreationDate().toString()
                    ))
                    .collect(Collectors.toList());
            Files.write(FILE, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
