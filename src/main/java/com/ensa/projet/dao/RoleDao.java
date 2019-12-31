package com.ensa.projet.dao;

import com.ensa.projet.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao  extends JpaRepository<Role,Long> {

}
