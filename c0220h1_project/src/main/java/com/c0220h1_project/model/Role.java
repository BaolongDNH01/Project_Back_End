package com.c0220h1_project.model;

import com.c0220h1_project.model.constant.ERoleName;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "role_name")
    private ERoleName roleName;

    public Role() {
    }

    public Role(ERoleName eRoleName) {
        this.roleName = eRoleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(ERoleName eRoleName) {
        this.roleName = eRoleName;
    }
}
