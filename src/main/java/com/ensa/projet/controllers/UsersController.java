package com.ensa.projet.controllers;

import com.ensa.projet.metier.ServiceServ;
import com.ensa.projet.metier.UserService;
import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    ServiceServ serviceServ;
    @GetMapping(params = {"pageNo","pageSize","sortBy"})
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public Page<User> getUsers(int pageNo,int pageSize,String sortBy){
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        return userService.getAllUsers(pageable);
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(params = {"role","pageNo","pageSize","sortBy"})
    public Page<User> getUsersByRole(String role,int pageNo,int pageSize,String sortBy){
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        return userService.getUsersByRole(role,pageable);
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping
    public User createUser(@RequestBody  User user){

        return userService.createUser(user);
    }
    @PreAuthorize("hasRole('ROLE_MANAGER') or #id == authentication.principal.id")
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id){
        return userService.updateUser(user);
    }
    @PreAuthorize("hasRole('ROLE_MANAGER') or #id == authentication.principal.id")
    @GetMapping(value = "{id}/services",params = {"as","pageNo","pageSize","sortBy"})
    public Page<Servicee> getUserServices(@PathVariable long id,String as,int pageNo,int pageSize,String sortBy){
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        if (as.equals("chef")) {
            return serviceServ.getUserServicesAsChef(id,pageable);
        }else {
            return serviceServ.getUserServicesAsEmployee(id,pageable);
        }
   }
    @PreAuthorize("hasRole('ROLE_MANAGER') or #id == authentication.principal.id")
    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){
        return userService.getUserById(id);
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(value = "/search",params = {"by","value","pageNo","pageSize","sortBy"})
    public Page<User> getUserByKeyword(String by, String value, int pageNo, int pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        return userService.searchUsers(by,value,pageable);
    }
}
