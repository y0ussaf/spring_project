package com.ensa.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    private String content;
    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date updated_at;
    @ManyToOne
    @JsonIgnore
    private Task task;
    @JsonIgnore
    @OneToOne()
    private User user;
    private Status status;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public enum Status {
        URGENT,
        QUOTIDIEN,
        INFORMATIF
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    @JsonProperty
    public Task getTask() {
        return task;
    }
    @JsonIgnore
     public void setTask(Task task) {
        this.task = task;
    }
    @JsonProperty
    public User getUser() {
        return user;
    }
    @JsonIgnore
    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
