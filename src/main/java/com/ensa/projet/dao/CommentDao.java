package com.ensa.projet.dao;

import com.ensa.projet.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Long> {
    Page<Comment> findAllByTaskId(long task_id, Pageable pageable);
    Page<Comment> findAllByTaskIdAndStatus(long task_id, Comment.Status status,Pageable pageable);
}
