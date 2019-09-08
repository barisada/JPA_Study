package jpa.study.chapter5;

import jpa.study.chapter5.entity.unidirection.UniMember;
import jpa.study.chapter5.entity.unidirection.UniTeam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class UniMain {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastudy");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {


            tx.begin(); //트랜잭션 시작
            save(em);

            //목록조회
            List<UniMember> members = em.createQuery("select m from UniMember m", UniMember.class).getResultList();
            System.out.println("find : " + members);

            //객체그래프탐색 활용한 조회
            UniMember member = em.find(UniMember.class, "member1");
            UniTeam team = member.getTeam();
            System.out.println("team : " + team);

            //JPQL 조인 검색
            queryLogicJoin(em);

            //수정
            updateRelation(em);
            //수정 후 목록조회
            members = em.createQuery("select m from UniMember m", UniMember.class).getResultList();
            List<UniTeam> teams = em.createQuery("select m from UniTeam m", UniTeam.class).getResultList();
            System.out.println("after update...members : " + members);
            System.out.println("after update...teams : " + teams);

            //연관관계 삭제
            deleteRelation(em);
            //연관관게 삭제 후 목록조회
            members = em.createQuery("select m from UniMember m", UniMember.class).getResultList();
            teams = em.createQuery("select m from UniTeam m", UniTeam.class).getResultList();
            System.out.println("after deleting relation...members : " + members);
            System.out.println("after deleting relation...teams : " + teams);

            //엔티티 삭제
            UniMember member1 = em.find(UniMember.class, "member1");
            UniMember member2 = em.find(UniMember.class, "member2");
            member1.setTeam(null);
            member2.setTeam(null);
            em.remove(team);    //팀 삭제. 기존에 있떤 연관관계를 먼저 제거해야함. 그렇지 않으면 제약조건으로 인해 DB 오류 발생.
            //엔티티 삭제 목록조회
            members = em.createQuery("select m from UniMember m", UniMember.class).getResultList();
            teams = em.createQuery("select m from UniTeam m", UniTeam.class).getResultList();
            System.out.println("after deleting Entity...members : " + members);
            System.out.println("after deleting Entity...teams : " + teams);


            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static void deleteRelation(EntityManager em) {
        UniMember member1 = em.find(UniMember.class, "member1");
        member1.setTeam(null); //연관관계 제거
    }

    private static void updateRelation(EntityManager em) {
        //새로운 팀2
        UniTeam team2 = new UniTeam("team2", "팀2");
        em.persist(team2);

        //회원1 에게 팀2 설정
        UniMember member = em.find(UniMember.class, "member1");
        member.setTeam(team2);
    }

    private static void queryLogicJoin(EntityManager em) {
        String jpql = "select m from UniMember m join m.team t where t.name=:teamName";
        List<UniMember> result = em.createQuery(jpql, UniMember.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        result.forEach(m -> System.out.println("[query] member.name=" + m.getName()));
    }

    private static void save(EntityManager em) {
        UniTeam team1 = new UniTeam("team1", "팀1");
        em.persist(team1);

        UniMember member1 = new UniMember("member1", "회원1", null);
        UniMember member2 = new UniMember("member2", "회원2", null);
        member1.setTeam(team1);
        member2.setTeam(team1);

        em.persist(member1);
        em.persist(member2);
    }
}
