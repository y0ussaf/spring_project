package com.ensa.projet.dao;

import com.ensa.projet.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface CommentDao extends PagingAndSortingRepository<Comment,Long> {
    Page<Comment> findAllByTaskId(long task_id, Pageable pageable);
    Page<Comment> findAllByTaskIdAndStatus(long task_id, Comment.Status status,Pageable pageable);
}
