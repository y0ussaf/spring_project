package com.ensa.projet.metier;

import com.ensa.projet.dao.ServiceDao;
import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServImpl implements ServiceServ {
    private final
    UserService userService;
    private final ServiceDao serviceDao;
    public ServiceServImpl(ServiceDao serviceDao, UserService userService) {
        this.serviceDao = serviceDao;
        this.userService = userService;
    }

    @Override
    public Page<Servicee> getUserServicesAsChef(long user_id, Pageable pageable) {
        return serviceDao.findAllByChefId(user_id, pageable);
    }

    @Override
    public Page<Servicee> getUserServicesAsEmployee(long user_id, Pageable pageable) {
        User user = userService.getUserById(user_id);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        return serviceDao.findAllByEmployeesContains(userList,pageable);
    }


    @Override
    public Servicee getServiceById(long service_id) {
        return serviceDao.findById(service_id)
                .orElseThrow(()-> new EntityNotFoundException("service not found"));
    }

    @Override
    public void addService(Servicee service) {
        serviceDao.save(service);
    }

    @Override
    public void updateService(Servicee servicee) {
        serviceDao.save(servicee);
    }
}
