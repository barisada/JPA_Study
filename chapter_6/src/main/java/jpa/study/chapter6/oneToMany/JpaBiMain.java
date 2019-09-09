package jpa.study.chapter6.oneToMany;

import jpa.study.chapter6.oneToMany.entity.OneToManyBiMember;
import jpa.study.chapter6.oneToMany.entity.OneToManyBiTeam;
import jpa.study.chapter6.oneToMany.entity.OneToManyUniMember;
import jpa.study.chapter6.oneToMany.entity.OneToManyUniTeam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaBiMain {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastudy");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
            biDirectionSave(em);
            //목록조회
            List<OneToManyBiMember> members = em.createQuery("select m from OneToManyBiMember m", OneToManyBiMember.class).getResultList();
            System.out.println("find : " + members);
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
     * 최신버전에선 fk 없어서(?) 에러발생...ERROR: Referential integrity constraint violation:
     * @param em
     */
    private static void biDirectionSave(EntityManager em) {
        OneToManyBiMember member1 = new OneToManyBiMember();
        OneToManyBiMember member2 = new OneToManyBiMember();
        member1.setUsername("member1");
        member2.setUsername("member2");

        OneToManyBiTeam team = new OneToManyBiTeam();
        team.setName("team1");

        team.getMember().add(member1);
        member1.setTeam(team);

        team.getMember().add(member2);
        member2.setTeam(team);

        em.persist(member1);    //insert member1 ==> fk 가 없어서 에러발생
        em.persist(member2);    //insert member2
        em.persist(team);       //insert team & update member1.fk,& update member2.fk
    }
}
