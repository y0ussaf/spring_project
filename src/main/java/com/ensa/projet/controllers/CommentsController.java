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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController

@RequestMapping("/services/{service_id}/tasks/{task_id}/comments")
public class CommentsController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_MANAGER') " +
            "or serviceServImpl.isServiceChef(#service_id,authentication.principal.id)" +
            "or serviceServImpl.isServiceEmployee(#service_id,authentication.principal.id)" )
    @GetMapping(params = {"pageNo","pageSize","sortBy"})
    public Page<Comment> getAllServiceEmployees(int pageNo, int pageSize, String sortBy, @PathVariable long task_id, @PathVariable String service_id){
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        return commentService.getComments(task_id,pageable);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER') " +
            "or serviceServImpl.isServiceChef(#service_id,authentication.principal.id)" +
            "or serviceServImpl.isServiceEmployee(#service_id,authentication.principal.id)" )
    @PostMapping
    public Comment createComment(@RequestBody Comment comment, @PathVariable long task_id, @PathVariable String service_id,@CurrentUser UserPrincipal principal){
        User user = userService.getUserById(principal.getId());
        comment.setUser(user);
        return commentService.addComment(task_id,comment);
    }

    @PreAuthorize("comment.user.id == authentication.principal.id" )
    @PutMapping
    public Comment updateComment(@RequestBody Comment comment){
         return commentService.updateComment(comment);
    }
}
