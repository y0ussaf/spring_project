package com.ensa.projet.dao;

import com.ensa.projet.models.Notification;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NotificationDao  extends PagingAndSortingRepository<Notification, Long> {
    List<Notification> findAllByUserId(long user_id);
}
