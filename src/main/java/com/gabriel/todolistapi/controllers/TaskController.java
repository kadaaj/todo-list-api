package com.gabriel.todolistapi.controllers;

import com.gabriel.todolistapi.entities.Task;
import com.gabriel.todolistapi.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task newTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @GetMapping
    public List<Task> listTasks() {
        return taskService.listTasks();
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,@Valid @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}