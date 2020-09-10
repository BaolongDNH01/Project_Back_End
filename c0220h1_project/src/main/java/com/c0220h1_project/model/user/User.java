package com.c0220h1_project.model.user;

import com.c0220h1_project.model.Exam;
import com.c0220h1_project.model.Role;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
    name = "_user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
    }
)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Integer id;

    @NotBlank
    private String username;

    @NotBlank
    @Column(name = "user_password")
    private String userPassword;

    @NotBlank
    @Column(name ="full_name")
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    private String avatar;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable (
        name ="user_role",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Exam> examList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    public User(@NotBlank String username, @NotBlank String userPassword, @NotBlank String fullName, @NotBlank String email, @NotBlank String address, @NotBlank String phoneNumber, String avatar, Set<Role> roles) {
        this.username = username;
        this.userPassword = userPassword;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.roles = roles;
    }

    public User() {
    }
}
