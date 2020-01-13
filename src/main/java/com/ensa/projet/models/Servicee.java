package com.ensa.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
@Entity
public class Servicee {
    public Servicee() {
    }

    public Servicee(String name, LocalDate estimatedEndDate) {
        this.name = name;
        this.estimatedEndDate = estimatedEndDate;
    }

    @Id @GeneratedValue()
    private long id;
    private String name;
    @CreationTimestamp
    private LocalDate created_at;
    @UpdateTimestamp
    private LocalDate updated_at;
    private LocalDate endDate;
    private LocalDate estimatedEndDate;
    @ManyToOne()
    private User chef;
    @JsonIgnore

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Collection<Task> tasks =new ArrayList<>();
    @ManyToMany
    @JsonIgnore
    private Collection<User> employees = new ArrayList<>();
    private Status status;

    public LocalDate getEstimatedEndDate() {
        return estimatedEndDate;
    }

    public void setEstimatedEndDate(LocalDate estimatedEndDate) {
        this.estimatedEndDate = estimatedEndDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public enum Status {
        VALIDE,
        ANNULE,
        EN_COURS
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public User getChef() {
        return chef;
    }

    public void setChef(User chef) {
        this.chef = chef;
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }

    public Collection<User> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<User> employees) {
        this.employees = employees;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
