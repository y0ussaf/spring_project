package com.ensa.projet.dao;

 import com.ensa.projet.models.Servicee;
 import com.ensa.projet.models.Task;
 import com.ensa.projet.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.PagingAndSortingRepository;
 import org.springframework.stereotype.Repository;

 import javax.transaction.Transactional;
 import java.util.Collection;

@Repository
@Transactional
public interface ServiceDao extends PagingAndSortingRepository<Servicee,Long> {
    @Query("select s from Servicee s join s.chef c where c.id = ?1")
    Page<Servicee> findServicesByChefId(long chef_id, Pageable pageable);
    @Query("select s from Servicee s join s.employees e where e.id = ?1")
    Page<Servicee> findServicesByEmployeeId(long employee_id,Pageable pageable);
    Page<Servicee> findAllByNameContaining(String name,Pageable pageable);
    Page<Servicee> findAllByStatus(Servicee.Status status, Pageable pageable);
    @Query("select  case when count(s) > 0 then true else false end  from Servicee s where s.id = ?1 and  s.chef.id = ?2")
    boolean isServiceChef(long id, long chef_id);
    @Query("select case when count(s) > 0 then true else false end " +
            "from Servicee s join s.employees e where s.id = ?1 and e.id =?2")
    boolean isServiceEmployee(long id, long employee_id);
    @Query("select case when count(s) > 0 then false else true end from Servicee s join s.tasks t " +
            "where s.id = ?1 and t.status <> ?2" )
    boolean isAllServiceTasksInSameStatus(long service_id,Task.Status status);
}
