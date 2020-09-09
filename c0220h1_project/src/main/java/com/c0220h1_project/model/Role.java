package com.c0220h1_project.model;

import com.c0220h1_project.model.constant.ERoleName;
import com.c0220h1_project.model.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "_role")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="role_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "role_name")
    private ERoleName roleName;

    @ManyToMany(targetEntity = User.class,fetch = FetchType.LAZY, mappedBy = "roles", cascade = CascadeType.ALL)
//    @JoinTable (
//            name ="user_role",
//            joinColumns = @JoinColumn(name="role_id"),
//            inverseJoinColumns = @JoinColumn(name="user_id")
//    )
//    @JsonBackReference
    Set<User> userSet = new HashSet<User>();

    public Role() {
    }

    public Role(ERoleName eRoleName) {
        this.roleName = eRoleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(ERoleName eRoleName) {
        this.roleName = eRoleName;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
