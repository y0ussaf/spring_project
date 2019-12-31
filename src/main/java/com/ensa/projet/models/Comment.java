package com.ensa.projet.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date updated_at;
    @ManyToOne
    private Task task;
    @OneToOne
    private User user;
    private Status status;
    public enum Status {
        URGENT,
        QUOTIDIEN,
        INFORMATIF
    }
}
