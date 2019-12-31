package com.ensa.projet.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Task {
    @Id @GeneratedValue
    private long id;
    private long num;
    private Date endDate;
    private Date startDate;
    private Status status;
    @ManyToOne
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

    public enum Status {
        VALIDE,
        NON_VALIVE,
        EN_ATTENTE,
        EN_COURS
    }
}

