package jpa.study.chapter5.entity.bidirection;

import javax.persistence.*;

@Entity
public class BiMember {

    @Id @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private BiTeam team;

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

    public BiTeam getTeam() {
        return team;
    }

    //연관관계 설정, 연관관계 편의 메소드
    public void setTeam(BiTeam team) {
        //기존 팀과 관계를 제거
        if(this.team !=  null){
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);
    }
}
