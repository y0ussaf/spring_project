package com.ensa.projet.dao;

import com.ensa.projet.models.Role;
import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends PagingAndSortingRepository<User, Long> {

    @Query("select u from User u join u.servicesAsChef s where s.id = ?1")
    User getServiceChef(long service_id);
    @Query("select u from User u join u.servicesAsEmployee s where s.id = ?1")
    Page<User> getServiceEmployees(long service_id, Pageable pageable);
    @Query("select u from User u join u.roles r where r.name = ?1")
    Page<User> findUsersByRole(String role,Pageable pageable);
    Page<User> findUsersByNomContaining(String nom, Pageable pageable);
    Optional<User> findUserByEmail(String email);

}
