package com.ensa.projet.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue
    private long id;
    public Notification() {
    }

    public Notification(String content, Task task, User user) {
        this.content = content;
        this.task = task;
        this.user = user;
    }

    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne()
    @JsonManagedReference()

    Task task;
    @JsonManagedReference()
    @OneToOne
    User user;
}
