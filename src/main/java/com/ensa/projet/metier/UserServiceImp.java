package com.ensa.projet.metier;

import com.ensa.projet.dao.UserDao;
import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserDao userDao;
    private final ServiceServ serviceServ;
    public UserServiceImp(UserDao userDao, ServiceServ serviceServ) {
        this.userDao = userDao;
        this.serviceServ = serviceServ;
    }


    public void addUser(User user){
        userDao.save(user);
    }
    public void updateUser(User user){
        userDao.save(user);
    }
    public void deleteUser(long userId){
        userDao.deleteById(userId);
    }
    public User getUserById(long user_id){
        return userDao.findById(user_id)
                .orElseThrow(()-> new EntityNotFoundException("user not found"));
    }
    @Override
    public Page<User> getServiceEmployees(long service_id, Pageable pageable) {
        Servicee service = serviceServ.getServiceById(service_id);
        List<Servicee> serviceList = new ArrayList<>();
        serviceList.add(service);
        return userDao.findAllByServicesAsEmployeeContaining(serviceList,pageable);
    }

}
