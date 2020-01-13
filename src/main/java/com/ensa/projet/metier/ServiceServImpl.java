package com.ensa.projet.metier;

import com.ensa.projet.dao.ServiceDao;
import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.Task;
import com.ensa.projet.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ServiceServImpl implements ServiceServ {
    @Autowired
    UserService userService;
    @Autowired
    ServiceDao serviceDao;



    @Override
    public Page<Servicee> getUserServicesAsChef(long user_id, Pageable pageable) {
        return serviceDao.findServicesByChefId(user_id, pageable);
    }

    @Override
    public Page<Servicee> getUserServicesAsEmployee(long user_id, Pageable pageable) {
        return serviceDao.findServicesByEmployeeId(user_id,pageable);
    }

    @Override
    public Page<User> getServiceEmployees(long service_id,Pageable pageable) {
        return userService.getServiceEmployees(service_id,pageable);
    }

    @Override
    public User getServiceChef(long service_id) {

        return  getServiceById(service_id).getChef();
    }


    @Override
    public Servicee getServiceById(long service_id) {
        return serviceDao.findById(service_id)
                .orElseThrow(()-> new EntityNotFoundException("service not found"));
    }

    @Override
    public Page<Servicee> getAllServices(Pageable pageable) {

        return serviceDao.findAll(pageable);
    }

    @Override
    public void deleteService(long service_id)
    {
        serviceDao.deleteById(service_id);
    }

    @Override
    public Servicee createOrUpdateService(Servicee servicee) throws NotAllServiceTasksValid {
        if (servicee.getStatus() !=getServiceById(servicee.getId()).getStatus() && (servicee.getStatus() == Servicee.Status.VALIDE )){
            if (isAllServiceTasksValid(servicee.getId())){
                return serviceDao.save(servicee);
            }else {
                throw new  NotAllServiceTasksValid("no valid");
            }
        }else {
            return serviceDao.save(servicee);
        }

    }

    @Override
    public void deleteEmployeeFromService(long service_id, long employee_id) {
        Servicee servicee =getServiceById(service_id);
        servicee.getEmployees().removeIf(user -> user.getId() == employee_id);
        serviceDao.save(servicee);
    }

    @Override
    public Servicee addEmployeesToService(long service_id, List<Long> employees) {
        Servicee servicee = getServiceById(service_id);
        List<User> empls = userService.getUsersById(employees);
        List<User> newEmployees = (List<User>) servicee.getEmployees();
        newEmployees.addAll(empls);
        servicee.setEmployees(newEmployees);
        return serviceDao.save(servicee);
    }

    @Override
    public boolean isAllServiceTasksValid(long service_id) {
        return serviceDao.isAllServiceTasksInSameStatus(service_id, Task.Status.VALIDE);
    }

    @Override
    public Page<Servicee> searchServices(String by, String value, Pageable pageable) {
        return serviceDao.findAllByNameContaining(value,pageable);
    }

    @Override
    public boolean isServiceChef(long service_id, long chef_id)
    {
        return serviceDao.isServiceEmployee(service_id,chef_id);
    }

    @Override
    public boolean isServiceEmployee(long service_id, long employee_id) {
        return serviceDao.isServiceEmployee(service_id, employee_id);
    }

    @Override
    public Page<Servicee> getAllServicesByStatus(Servicee.Status status, Pageable pageable) {
        return serviceDao.findAllByStatus(status, pageable);
    }
    @Scheduled(cron = "0 0 12 * * ?")
    public void notifyServiceChefs(){
        getAllServices(Pageable.unpaged()).stream().forEach(new Consumer<Servicee>() {
            @Override
            public void accept(Servicee servicee) {
                servicee.getTasks().stream().forEach(task -> {
                    LocalDate estimatedEndDate = task.getEstimatedEndDate();
                    LocalDate localDate1 = estimatedEndDate.minusDays(15);
                    if (LocalDate.now().isAfter(estimatedEndDate) && task.getNotification() == null){
                        User chef = task.getService().getChef();

                    }
                });
            }
        });
    }

}
