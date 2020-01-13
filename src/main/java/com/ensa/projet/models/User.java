package com.ensa.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Entity
public class User {
    public User() {
    }

    public User(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }
    @JsonInclude
    @Id
    @GeneratedValue
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    @JsonIgnore
    @ManyToMany(mappedBy = "chef")
    private Collection<Servicee> servicesAsChef;
    @JsonIgnore
    @ManyToMany(mappedBy = "employees")
    private Collection<Servicee> servicesAsEmployee;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles;
    @OneToMany(mappedBy = "user")
    private Collection<Notification> notifications;
    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<Servicee> getServicesAsChef() {
        return servicesAsChef;
    }

    public void setServicesAsChef(Collection<Servicee> servicesAsChef) {
        this.servicesAsChef = servicesAsChef;
    }

    public Collection<Servicee> getServicesAsEmployee() {
        return servicesAsEmployee;
    }

    public void setServicesAsEmployee(Collection<Servicee> servicesAsEmployee) {
        this.servicesAsEmployee = servicesAsEmployee;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
