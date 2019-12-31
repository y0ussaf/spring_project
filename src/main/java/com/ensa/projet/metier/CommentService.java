package com.ensa.projet.metier;

import com.ensa.projet.models.Comment;
import org.springframework.data.domain.Page;

public interface CommentService {
    Page<Comment> getComments(long task_id);
    Page<Comment> getCommentsByStatus(Comment.Status status);
    void addComment(Comment comment:);
}
