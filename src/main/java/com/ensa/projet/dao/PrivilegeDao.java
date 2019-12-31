package com.ensa.projet.dao;

import com.ensa.projet.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeDao extends JpaRepository<Privilege,Long> {
}
