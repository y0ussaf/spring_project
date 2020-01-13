package com.ensa.projet.dao;

import com.ensa.projet.models.Notification;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NotificationDao  extends PagingAndSortingRepository<Notification, Long> {

}
