package jpa.study.chapter5.entity.object;

public class PureMember {
    public PureMember(String id, String name, PureTeam team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }


    private String id;
    private String name;
    private PureTeam team;

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

    public PureTeam getTeam() {
        return team;
    }

    public void setTeam(PureTeam team) {
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
