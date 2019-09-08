package jpa.study.chapter5.entity.bidirection;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BiTeam {

    @Id @Column(name = "TEAM_ID")
    private String id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<BiMember> members = new ArrayList<>();

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

    public List<BiMember> getMembers() {
        return members;
    }

    public void setMembers(List<BiMember> members) {
        this.members = members;
    }
}
