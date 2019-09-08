package jpa.study.chapter5;

import jpa.study.chapter5.entity.bidirection.BiMember;
import jpa.study.chapter5.entity.bidirection.BiTeam;
import jpa.study.chapter5.entity.unidirection.UniMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class BiMain {

    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastudy");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득
        try {
            tx.begin(); //트랜잭션 시작

            //엔티티 초기구성
            init(em);

            //목록조회
            List<BiMember> members = em.createQuery("select m from BiMember m", BiMember.class).getResultList();
            List<BiTeam> teams = em.createQuery("select m from BiTeam m", BiTeam.class).getResultList();
            System.out.println("find members: " + members);
            System.out.println("find teams: " + teams);
            teams.forEach(team -> {
                System.out.println(team.getName() + " 's member");
                team.getMembers().forEach(m -> System.out.println(m.getUsername()));
            });

            //양방향으로 객체 그래프 탐색
            biDirection(em);


            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static void biDirection(EntityManager em) {
        BiTeam team = em.find(BiTeam.class, "team1");
        List<BiMember> members = team.getMembers(); //(팀 -> 회원) 객체 그래프 탐색
        System.out.println("members size : " +  members.size());
        members.forEach(m -> System.out.println("[member.username] = " + m.getUsername()));
    }

    private static void init(EntityManager em) {
        BiTeam team1 = new BiTeam();
        team1.setId("team1");
        team1.setName("팀1");
        BiTeam team2 = new BiTeam();
        team2.setId("team2");
        team2.setName("팀2");

        em.persist(team1);
        em.persist(team2);

        BiMember member1 = new BiMember();
        member1.setId("member1");
        member1.setUsername("회원1");
        member1.setTeam(team1);
        //team1.getMembers().add(member1);    //양방향 모두 관계를 설정해야함 ==> member.setTeam 메소드에서 처리

        BiMember member2 = new BiMember();
        member2.setId("member2");
        member2.setUsername("회원2");
        member2.setTeam(team1);
        //team1.getMembers().add(member2);    //양방향 모두 관계를 설정해야함 ==> member.setTeam 메소드에서 처리

        em.persist(member1);
        em.persist(member2);
    }
}
