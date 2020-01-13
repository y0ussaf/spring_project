package com.ensa.projet.dao;

 import com.ensa.projet.models.Task;
import com.ensa.projet.models.Task.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.repository.PagingAndSortingRepository;
 import org.springframework.stereotype.Repository;

 import javax.transaction.Transactional;
 import java.util.Collection;
import java.util.List;
@Repository
@Transactional
public interface TaskDao  extends PagingAndSortingRepository<Task,Long> {
     Page<Task> findAllByServiceId(long service_id, Pageable pageable);
     Page<Task> findAllByServiceIdAndStatus(long service_id, Status status,Pageable pageable);
     Task findByIdAndServiceId(long id, long service_id);

}
