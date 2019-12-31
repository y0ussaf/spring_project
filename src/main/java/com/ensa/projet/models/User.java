package com.ensa.projet.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    @ManyToMany(mappedBy = "chef")
    private Collection<Service> servicesAsChef;
    @ManyToMany(mappedBy = "employees")
    private Collection<Service> servicesAsEmployee;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
}
