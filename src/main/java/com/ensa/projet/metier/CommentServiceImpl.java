package com.ensa.projet.metier;

import com.ensa.projet.dao.CommentDao;
import com.ensa.projet.models.Comment;
import com.ensa.projet.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CommentServiceImpl implements CommentService {
    final
    TaskService taskService;
    final
    CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao, TaskService taskService) {
        this.commentDao = commentDao;
        this.taskService = taskService;
    }

    @Override
    public Page<Comment> getComments(long task_id,Pageable pageable) {
        return commentDao.findAllByTaskId(task_id, Pageable.unpaged());
    }

    @Override
    public Page<Comment> getCommentsByStatus( long task_id, Comment.Status status,Pageable pageable) {

        return commentDao.findAllByTaskIdAndStatus(task_id,status,pageable);
    }

    @Override
    public Comment addComment(long task_id, Comment comment) {
        Task task = taskService.getTaskById(task_id);
        comment.setTask(task);
        return commentDao.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public void deleteComment( long comment_id) {
        commentDao.deleteById(comment_id);
    }

    @Override
    public Comment findById(long comment_id) {
        return commentDao.findById(comment_id).orElseThrow(EntityNotFoundException::new);
    }
}
