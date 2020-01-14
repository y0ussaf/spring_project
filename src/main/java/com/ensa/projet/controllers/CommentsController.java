package com.ensa.projet.controllers;


import com.ensa.projet.metier.CommentService;
import com.ensa.projet.metier.UserService;
import com.ensa.projet.models.Comment;
import com.ensa.projet.models.User;
import com.ensa.projet.security.CurrentUser;
import com.ensa.projet.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;

@RestController

@RequestMapping("/services/{service_id}/tasks/{task_id}/comments")
public class CommentsController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ROLE_MANAGER') " +
            "or @serviceServImpl.isServiceChef(#service_id,authentication.principal.id)" +
            "or @serviceServImpl.isServiceEmployee(#service_id,authentication.principal.id)" )
    @GetMapping(params = {"pageNo","pageSize","sortBy"})
    public Page<Comment> getAllServiceEmployees(int pageNo, int pageSize, String sortBy, @PathVariable long task_id, @PathVariable String service_id){
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        return commentService.getComments(task_id,pageable);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') " +
            "or @serviceServImpl.isServiceChef(#service_id,authentication.principal.id)" +
            "or @serviceServImpl.isServiceEmployee(#service_id,authentication.principal.id)" )
    @PostMapping
    public Comment createComment(@RequestBody Comment comment, @PathVariable long task_id, @PathVariable long service_id, Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        User user = userService.getUserById(userPrincipal.getId());
        comment.setUser(user);
        return commentService.addComment(task_id,comment);
    }

    @PutMapping("/{comment_id}")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable long comment_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Comment comment1 = commentService.findById(comment_id);
        if (userPrincipal.getId()!=comment1.getUser().getId()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        comment1.setStatus(comment.getStatus());
        comment1.setContent(comment.getContent());
         commentService.updateComment(comment1);
         return ResponseEntity.ok(comment1);
    }
    @DeleteMapping("/{comment_id}")
    @PreAuthorize("hasRole('ROLE_MANAGER') " +
            "or @serviceServImpl.isServiceChef(#service_id,authentication.principal.id)" +
            "or @serviceServImpl.isServiceEmployee(#service_id,authentication.principal.id)" )
    public void deleteComment(@PathVariable long comment_id){
        commentService.deleteComment(comment_id);
    }

}
