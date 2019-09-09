package jpa.study.chapter6.oneToMany.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OneToManyBiTeam {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;
    @OneToMany
    @JoinColumn(name = "MEMBER_ID")
    private List<OneToManyBiMember> member = new ArrayList<>();

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

    public List<OneToManyBiMember> getMember() {
        return member;
    }

    public void setMember(List<OneToManyBiMember> member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "OneToManyTeam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
