package com.ensa.projet.metier;

import com.ensa.projet.dao.TaskDao;
import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Date;

@Service
public class TaskServiceImpl implements TaskService {
    final
    private TaskDao taskDao;
    final
    private ServiceServ serviceServ;

    public TaskServiceImpl(ServiceServ serviceServ, TaskDao taskDao) {
        this.serviceServ = serviceServ;
        this.taskDao = taskDao;
    }

    @Override
    public Page<Task> getServiceTasks(long service_id, Pageable pageable) {
        return taskDao.findAllByServiceId(service_id,pageable);
    }

    @Override
    public Page<Task> getServiceTasksByStatus(long service_id,Task.Status status,Pageable pageable)
    {
        return taskDao.findAllByServiceIdAndStatus(service_id,status,pageable);
    }

    @Override
    public Task addTask(long service_id, Task task) {
        Servicee servicee = serviceServ.getServiceById(service_id);
        task.setService(servicee);
        return taskDao.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        if (task.getStatus() == Task.Status.VALIDE){
            task.setEndDate(new Date(System.currentTimeMillis()));
        }
        return taskDao.save(task);
    }

    @Override
    public void deleteTask(long task_id) {
        taskDao.deleteById(task_id);
    }

    @Override
    public Task getTaskById(long task_id) {
        return taskDao.findById(task_id).orElseThrow(EntityNotFoundException::new);
    }


}
