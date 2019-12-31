package com.ensa.projet.dao;

import com.ensa.projet.models.Role;
import com.ensa.projet.models.Service;
import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
 import java.util.Collection;
import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    Page<User> findAllByRolesContains(Collection<Role> roles, Pageable pageable);
    Page<User> findAllByServicesAsEmployeeContaining(List<Servicee> servicesAsEmployee, Pageable pageable);
    Page<User> findAllByServicesAsChefContaining(Collection<Service> servicesAsEmployee,Pageable pageable);
}
