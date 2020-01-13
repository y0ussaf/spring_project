package com.ensa.projet.dao;

import com.ensa.projet.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public interface RoleDao  extends PagingAndSortingRepository<Role,Long> {

    List<Role> findAllByNameIn(Collection<String> names);
}
