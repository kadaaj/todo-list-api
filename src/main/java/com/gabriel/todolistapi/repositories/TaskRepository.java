package com.gabriel.todolistapi.repositories;

import com.gabriel.todolistapi.entities.Task;
import com.gabriel.todolistapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);
}