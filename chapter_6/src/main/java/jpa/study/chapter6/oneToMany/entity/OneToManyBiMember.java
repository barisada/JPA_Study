package jpa.study.chapter6.oneToMany.entity;

import javax.persistence.*;

@Entity
public class OneToManyBiMember {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long Id;
    private  String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private OneToManyBiTeam team;

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

    public OneToManyBiTeam getTeam() {
        return team;
    }

    public void setTeam(OneToManyBiTeam team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "OneToManyBiMember{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", team=" + team +
                '}';
    }
}
