package jpa.study.chapter4.entity;

import jpa.study.chapter4.RoleType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"NAME", "AGE"})})
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable =  false, length = 10)
    private String username;

    //컬럼 명이랑 자동 맵핑
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createDate;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime lastModifiedDate;

    @Lob
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
