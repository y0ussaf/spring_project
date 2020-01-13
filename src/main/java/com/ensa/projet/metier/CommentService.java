package com.ensa.projet.metier;

import com.ensa.projet.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Page<Comment> getComments(long task_id, Pageable pageable);
    Page<Comment> getCommentsByStatus( long task_id,Comment.Status status,Pageable pageable);
    Comment addComment(long task_id, Comment comment);
    Comment updateComment(Comment comment);
    void deleteComment(long comment_id);
    Comment findById(long comment_id);

}
