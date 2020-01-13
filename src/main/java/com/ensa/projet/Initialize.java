package com.ensa.projet;

import com.ensa.projet.dao.*;
import com.ensa.projet.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Component
public class Initialize  implements ApplicationRunner {
    @Autowired
    RoleDao roleDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    TaskDao taskDao;
    @Autowired
    ServiceDao serviceDao;
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
             Role role = new Role();
            role.setName("ROLE_MANAGER");
            Role role2 = new Role();
            role2.setName("ROLE_EMPLOYEE");
            roleDao.save(role);roleDao.save(role2);
            User manager = new User("youssef","you","you@gmail.com",passwordEncoder.encode("0000"));
            ArrayList<Role> userRoles = new ArrayList<>();
            userRoles.add(role);
            manager.setRoles(userRoles);
            userDao.save(manager);
            userRoles.clear();
            userRoles.add(role2);
            ArrayList<User> employees = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                User employee = new User("employee "+i,"prenom","employee"+i+"@gmail.com",passwordEncoder.encode("0000"));
                employee.setRoles(userRoles);
                employees.add(userDao.save(employee));
            }

            Servicee servicee1 = new Servicee("service 1",LocalDate.of(2021,10,23));
            servicee1.setChef(employees.get(0));
            servicee1.setEmployees(employees.subList(1,3));
            servicee1.setStatus(Servicee.Status.EN_COURS);
            serviceDao.save(servicee1);
            Servicee servicee2 = new Servicee("service 2",LocalDate.of(2022,4,23));
            servicee2.setChef(employees.get(4));
            servicee2.setStatus(Servicee.Status.VALIDE);
            servicee2.setEmployees(employees.subList(5,employees.size()));
            serviceDao.save(servicee2);
            ArrayList<Task> tasks = new ArrayList<>();
            for (int i = 0; i <3 ; i++) {
                Task task = new Task(34,"task "+i,LocalDate.of(2020,2,21),i == 0? Task.Status.EN_COURS : Task.Status.EN_ATTENTE);
                task.setService(servicee1);
                taskDao.save(task);
            }
            for (int i = 3; i <8 ; i++) {
                Task task = new Task(34,"task "+i,LocalDate.of(2023,1,3),i == 4? Task.Status.EN_COURS : Task.Status.EN_ATTENTE);
                task.setService(servicee2);
                taskDao.save(task);
            }





    }
}
