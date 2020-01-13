package com.ensa.projet.metier;

import com.ensa.projet.dao.RoleDao;
import com.ensa.projet.dao.ServiceDao;
import com.ensa.projet.dao.UserDao;
import com.ensa.projet.models.Role;
import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.User;
import com.ensa.projet.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImp implements UserService , UserDetailsService {
    private final
    UserDao userDao;
    final
    RoleDao roleDao;
    final
    ServiceDao serviceDao;
    final
    PasswordEncoder passwordEncoder;
    public UserServiceImp(UserDao userDao, RoleDao roleDao, ServiceDao serviceDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.serviceDao = serviceDao;
        this.passwordEncoder = passwordEncoder;
    }


    public User createUser(User user){
        return userDao.save(user);
    }
    public User updateUser(User user){
        if (user.getPassword() == null) {
            user.setPassword(passwordEncoder.encode(getUserById(user.getId()).getPassword()));
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userDao.save(user);
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
        return userDao.getServiceEmployees(service_id,pageable);
    }
    @Override
    public User getServiceChef(long service_id) {
        return userDao.getServiceChef(service_id);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public Page<User> getUsersByRole(String role, Pageable pageable) {
        return userDao.findUsersByRole(role,pageable);
    }




    @Override
    public List<User> getUsersById(List<Long> users_ids) {
        return (List<User>) userDao.findAllById(users_ids);
    }

    @Override
    public Page<User> searchUsers(String by, String value, Pageable pageable) {
        if (by.equals("name")){
            return userDao.findUsersByNomContaining(value,pageable);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(s)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found email : " + s)
                );

        return UserPrincipal.create(user);
    }
    public UserDetails loadUserById(Long id) {
        User user = userDao.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }
}

