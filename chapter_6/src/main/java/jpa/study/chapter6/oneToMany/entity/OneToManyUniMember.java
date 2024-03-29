package jpa.study.chapter6.oneToMany.entity;

import javax.persistence.*;

public class OneToManyUniMember {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long Id;
    private  String username;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "OneToManyMember{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                '}';
    }
}
