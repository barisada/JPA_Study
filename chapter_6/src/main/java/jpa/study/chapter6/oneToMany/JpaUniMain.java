package jpa.study.chapter6.oneToMany;

import jpa.study.chapter6.oneToMany.entity.OneToManyUniMember;
import jpa.study.chapter6.oneToMany.entity.OneToManyUniTeam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaUniMain {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastudy");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
            save(em);
            tx.commit();//트랜잭션 커밋



        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    /**
     * 최신 버전에서 oneToMany 단방향 시도시 에러 발생 Referential integrity constraint violation: FK...
     * @param em
     */
    private static void save(EntityManager em) {
        OneToManyUniMember member1 = new OneToManyUniMember();
        OneToManyUniMember member2 = new OneToManyUniMember();
        member1.setUsername("member1");
        member2.setUsername("member2");

        OneToManyUniTeam team = new OneToManyUniTeam();
        team.setName("team1");
        team.getMember().add(member1);
        team.getMember().add(member2);

        em.persist(member1);    //insert member1 ==> fk 가 없어서 에러발생
        em.persist(member2);    //insert member2
        em.persist(team);       //insert team & update member1.fk,& update member2.fk
    }
}
