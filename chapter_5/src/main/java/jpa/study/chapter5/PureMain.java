package jpa.study.chapter5;

import jpa.study.chapter5.entity.object.PureMember;
import jpa.study.chapter5.entity.object.PureTeam;

public class PureMain {

    public static void main(String[] args) {
        PureMember member1 = new PureMember("member1", "회원1", null);
        PureMember member2 = new PureMember("member2", "회원2", null);
        PureTeam team1 = new PureTeam("team1", "팀1");

        member1.setTeam(team1);
        member2.setTeam(team1);

        PureTeam findTeam = member1.getTeam();  //객체 참조를 사용해서 연관관계를 탐색. 객체 그래프 탐색

        System.out.println("find Team : " + findTeam);
    }
}
