package com.ensa.projet.controllers;


import com.ensa.projet.controllers.forms.AddEmployeesForm;
import com.ensa.projet.dao.ServiceDao;
import com.ensa.projet.dao.TaskDao;
import com.ensa.projet.metier.NotAllServiceTasksValid;
import com.ensa.projet.metier.ServiceServ;
import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.Task;
import com.ensa.projet.models.User;
import com.ensa.projet.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController {
    @Autowired
    ServiceServ serviceServ;
    @Autowired
    ServiceDao serviceDao;
    @Autowired
    TaskDao taskDao;
    @GetMapping(value = "{service_id}/employees",params = {"pageNo","pageSize","sortBy"})
    public Page<User> getAllServiceEmployees(int pageNo, int pageSize, String sortBy, @PathVariable long service_id){
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        return serviceServ.getServiceEmployees(service_id,pageable);
    }

    @PreAuthorize(" @serviceServImpl.isServiceChef(#service_id,authentication.principal.id) or hasRole('ROLE_MANAGER') ")
    @DeleteMapping(value = "{service_id}/employees/{employee_id}")
    public void deleteEmployeeFromService(@PathVariable long service_id, @PathVariable long employee_id){
        serviceServ.deleteEmployeeFromService(service_id,employee_id);
    }
    @PostMapping(value = "{service_id}/employees")
    @PreAuthorize(" @serviceServImpl.isServiceChef(#service_id,authentication.principal.id) or hasRole('ROLE_MANAGER') ")

    public Servicee addEmployeeToService(@PathVariable long service_id, @RequestBody AddEmployeesForm addEmployeesForm){
        return serviceServ.addEmployeesToService(service_id,addEmployeesForm.getEmployees_ids());
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(params = {"pageNo","pageSize","sortBy"})
    public Page<Servicee> getAllServices(int pageNo, int pageSize,String sortBy)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
        return serviceServ.getAllServices(pageable);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(params = {"status","pageNo","pageSize","sortBy"})
    public Page<Servicee> getAllServicesByStatus(Servicee.Status status,int pageNo, int pageSize, String sortBy)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
        return serviceServ.getAllServicesByStatus(status,pageable);
    }

    @GetMapping("{service_id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER') " +
            "or serviceServImpl.isServiceChef(#service_id,authentication.principal.id)" +
            "or serviceServImpl.isServiceEmployee(#service_id,authentication.principal.id)" )
    public Servicee getService(@PathVariable long service_id){
            return serviceServ.getServiceById(service_id);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping
    public Servicee createService(@RequestBody Servicee servicee) {
        return serviceServ.createService(servicee);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') ")
    @PutMapping("{service_id}")
    public Servicee updateService(@RequestBody Servicee servicee, @PathVariable long service_id) throws NotAllServiceTasksValid {
        servicee.setId(service_id);
        return serviceServ.updateService(servicee);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER') ")
    @DeleteMapping("{service_id}")
    public void deleteService(@PathVariable long service_id){
        serviceServ.deleteService(service_id);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER') ")
    @GetMapping("{service_id}/chef")
    public User getServiceChef(@PathVariable long service_id){
        return serviceServ.getServiceChef(service_id);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER') ")
    @GetMapping(value = "/search",params = {"by","value","pageNo","pageSize","sortBy"})
    public Page<Servicee> getUserByKeyword(String by, String value, int pageNo, int pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        return serviceServ.searchServices(by,value,pageable);
    }
    @PreAuthorize("hasAnyRole('ROLE_MANAGER') ")
    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return (List<Task>) taskDao.findAll();
    }
    @PreAuthorize("hasAnyRole('ROLE_MANAGER') ")
    @GetMapping(value = "/tasks",params = "status")
    public List<Task> getAllTasksByStatus(Task.Status status){
        return  taskDao.findAllByStatus(status);
    }
}
