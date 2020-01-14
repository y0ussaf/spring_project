package com.ensa.projet.controllers;

import com.ensa.projet.metier.ServiceServ;
import com.ensa.projet.metier.TaskService;
import com.ensa.projet.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services/{service_id}/tasks")
public class TasksController {
    @Autowired
    TaskService taskService;
    @Autowired
    ServiceServ serviceServ;
    @PreAuthorize("hasAnyRole('ROLE_MANAGER') " +
            "or @serviceServImpl.isServiceChef(#service_id,authentication.principal.id)" +
            "or @serviceServImpl.isServiceEmployee(#service_id,authentication.principal.id)" )
    @GetMapping(params = {"status","pageNo","pageSize","sortBy"})
    public Page<Task> getAllServiceTasksByStatus(@PathVariable long service_id, Task.Status status,int pageNo,int pageSize,String sortBy){
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        return taskService.getServiceTasksByStatus(service_id,status,pageable);
    }
    @PreAuthorize("hasAnyRole('ROLE_MANAGER') " +
            "or @serviceServImpl.isServiceChef(#service_id,authentication.principal.id)" +
            "or @serviceServImpl.isServiceEmployee(#service_id,authentication.principal.id)" )
    @GetMapping(params = {"pageNo","pageSize","sortBy"})
    public Page<Task> getAllServiceTasks(@PathVariable long service_id, int pageNo, int pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        return taskService.getServiceTasks(service_id,pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER') " +
            "or @serviceServImpl.isServiceChef(#service_id,authentication.principal.id)" +
            "or @serviceServImpl.isServiceEmployee(#service_id,authentication.principal.id)" )
    public  Task getTask( @PathVariable long service_id,@PathVariable long id)
    {
        return taskService.getTaskById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER') " +
            "or @serviceServImpl.isServiceChef(#service_id,authentication.principal.id)" +
            "or @serviceServImpl.isServiceEmployee(#service_id,authentication.principal.id)" )
    @PostMapping()
    public Task createTask(@PathVariable long service_id,@RequestBody Task task){
        return taskService.addTask(service_id,task);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER') " +
            "or @serviceServImpl.isServiceChef(#service_id,authentication.principal.id)" +
            "or @serviceServImpl.isServiceEmployee(#service_id,authentication.principal.id)" )
    @PutMapping("{task_id}")
    public Task updateTask(@PathVariable long service_id, @RequestBody Task task, @PathVariable long task_id){
        task.setId(task_id);
        task.setService(serviceServ.getServiceById(service_id));
        return taskService.updateTask(task);
    }
    @PreAuthorize("hasAnyRole('ROLE_MANAGER') " +
            "or @serviceServImpl.isServiceChef(#service_id,authentication.principal.id)" +
            "or @serviceServImpl.isServiceEmployee(#service_id,authentication.principal.id)" )
    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable long task_id,@PathVariable long service_id){
        taskService.deleteTask(task_id);
    }
}
