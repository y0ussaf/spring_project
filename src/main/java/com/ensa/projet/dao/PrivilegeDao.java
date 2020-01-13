package com.ensa.projet.dao;

import com.ensa.projet.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PrivilegeDao extends PagingAndSortingRepository<Privilege,Long> {
}
