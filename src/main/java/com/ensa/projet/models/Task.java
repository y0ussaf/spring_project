package com.ensa.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Task {
    public Task() {
    }

    public Task(long num, String description, LocalDate estimatedEndDate, Status status) {
        this.num = num;
        this.description = description;
        this.estimatedEndDate = estimatedEndDate;
        this.status = status;
    }

    @Id @GeneratedValue
    private long id;
    private long num;
    private String description;
    private Date endDate;
    @CreatedDate
    private Date startDate;
    private LocalDate estimatedEndDate;

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @OneToOne(mappedBy = "task")
    private Notification notification;
    private Status status;
    @ManyToOne
    @JsonIgnore
    private Servicee service;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Servicee getService() {
        return service;
    }

    public void setService(Servicee service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEstimatedEndDate() {
        return estimatedEndDate;
    }

    public void setEstimatedEndDate(LocalDate estimatedEndDate) {
        this.estimatedEndDate = estimatedEndDate;
    }

    public enum Status {
        VALIDE,
        NON_VALIDE,
        EN_ATTENTE,
        EN_COURS
    }
}

