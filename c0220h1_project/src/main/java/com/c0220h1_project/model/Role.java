package com.c0220h1_project.model;

import com.c0220h1_project.model.constant.ERoleName;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="role_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "role_name")
    private ERoleName roleName;

//    @ManyToMany
//    @JoinTable (
//            name ="user_role",
//            joinColumns = @JoinColumn(name="user_id"),
//            inverseJoinColumns = @JoinColumn(name="role_id")
//    )
//    @JsonBackReference
//    List<User> userSet;

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
}
