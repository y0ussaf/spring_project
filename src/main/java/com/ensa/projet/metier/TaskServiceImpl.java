package com.ensa.projet.metier;

import com.ensa.projet.dao.TaskDao;
import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final
    TaskDao taskDao;
    private final
    ServiceServ serviceServ;

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
    public void addTask(long service_id,Task task) {
        Servicee servicee = serviceServ.getServiceById(service_id);
        task.setService(servicee);
        taskDao.save(task);
    }

    @Override
    public void updateTask(Task task) {
        taskDao.save(task);
    }

    @Override
    public void deleteTask(long task_id) {

    }
}
