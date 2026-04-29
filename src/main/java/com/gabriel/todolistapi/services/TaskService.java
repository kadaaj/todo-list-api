package com.gabriel.todolistapi.services;

import com.gabriel.todolistapi.entities.Task;
import com.gabriel.todolistapi.entities.User;
import com.gabriel.todolistapi.exceptions.ResourceNotFoundException;
import com.gabriel.todolistapi.repositories.TaskRepository;
import com.gabriel.todolistapi.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    private User getLoggedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public Task createTask(Task task) {
        User user = getLoggedUser();

        task.setCompleted(false);
        task.setUser(user);

        return taskRepository.save(task);
    }

    public List<Task> listTasks() {
        User user = getLoggedUser();
        return taskRepository.findByUser(user);
    }

    public Task findById(Long id) {
        User user = getLoggedUser();

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }

        return task;
    }

    public Task updateTask(Long id, Task taskUpdated) {
        Task task = findById(id);

        task.setTitle(taskUpdated.getTitle());
        task.setDescription(taskUpdated.getDescription());
        task.setCompleted(taskUpdated.getCompleted());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }
}