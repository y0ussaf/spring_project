package com.ensa.projet.controllers;


import com.ensa.projet.dao.NotificationDao;
import com.ensa.projet.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/{user_id}/notifications")
public class NotificationsController {
    @Autowired
    NotificationDao notificationDao;
    @GetMapping
    public List<Notification> getUserNotifications(@PathVariable long user_id){
        return notificationDao.findAllByUserId(user_id);
    }
}
