package jpa.study.chapter5.entity.unidirection;

import javax.persistence.*;

@Entity
public class UniMember {

    public UniMember(String id, String name, UniTeam team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    @Id @Column(name = "MEMBER_ID")
    private String id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private UniTeam team;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UniTeam getTeam() {
        return team;
    }

    public void setTeam(UniTeam team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", team=" + team +
                '}';
    }
}
