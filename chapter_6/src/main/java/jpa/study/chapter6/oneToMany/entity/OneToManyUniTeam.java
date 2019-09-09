package jpa.study.chapter6.oneToMany.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class OneToManyUniTeam {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;
    @OneToMany
    @JoinColumn(name = "MEMBER_ID")
    private List<OneToManyUniMember> member = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OneToManyUniMember> getMember() {
        return member;
    }

    public void setMember(List<OneToManyUniMember> member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "OneToManyTeam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", member=" + member +
                '}';
    }
}
