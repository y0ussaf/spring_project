package com.ensa.projet.dao;

 import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface ServiceDao extends JpaRepository<Servicee,Long> {
    Page<Servicee> findAllByStatus(Servicee.Status status, Pageable pageable);
    Page<Servicee> findAllByChefId(long chef_id, Pageable pageable);
    Page<Servicee> findAllByEmployeesContains(Collection<User> employees, Pageable pageable);

}
