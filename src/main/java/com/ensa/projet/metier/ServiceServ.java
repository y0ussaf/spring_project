package com.ensa.projet.metier;

import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceServ {
    Page<Servicee> getUserServicesAsChef(long user_id, Pageable pageable);
    Page<Servicee> getUserServicesAsEmployee(long user_id,Pageable pageable);
    Page<User> getServiceEmployees(long service_id, Pageable pageable);
    User getServiceChef(long service_id);
    Servicee getServiceById(long service_id);
    Page<Servicee> getAllServices(Pageable pageable);
    void deleteService(long service_id);
    Servicee createOrUpdateService(Servicee servicee) throws NotAllServiceTasksValid;
    void deleteEmployeeFromService(long service_id, long employee_id);
    Servicee addEmployeesToService(long service_id,List<Long> employees);
    boolean isAllServiceTasksValid(long service_id);
    Page<Servicee> searchServices(String by, String value, Pageable pageable);


    boolean isServiceChef(long service_id, long chef_id);

    boolean isServiceEmployee(long service_id, long employee_id);
    Page<Servicee> getAllServicesByStatus(Servicee.Status status, Pageable pageable);
}
