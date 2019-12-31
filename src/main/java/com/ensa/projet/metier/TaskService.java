package com.ensa.projet.metier;

import com.ensa.projet.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    Page<Task> getServiceTasks(long service_id,Pageable pageable);
    Page<Task> getServiceTasksByStatus(long service_id,Task.Status status, Pageable pageable);
    void addTask(long service_id,Task task);
    void updateTask(Task task);
    void deleteTask(long task_id);

}
