package com.gabriel.todolistapi.services;

import com.gabriel.todolistapi.entities.Task;
import com.gabriel.todolistapi.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setCompleted(false);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskUpdated) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        task.setTitle(taskUpdated.getTitle());
        task.setDescription(taskUpdated.getDescription());
        task.setCompleted(taskUpdated.getCompleted());

        return taskRepository.save(task);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> listTasks() {
        return taskRepository.findAll();
    }
}