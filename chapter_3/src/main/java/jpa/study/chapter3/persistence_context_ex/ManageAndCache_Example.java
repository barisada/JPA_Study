package jpa.study.chapter3.persistence_context_ex;

import jpa.study.chapter3.JpaEntityManagerFactory;
import jpa.study.chapter3.Member;

import javax.persistence.EntityManager;

public class ManageAndCache_Example {
    public static void main(String[] args) {
        EntityManager em = JpaEntityManagerFactory.getEntityManager();

        //엔티티를 생성한 상태 (비영속)
        Member m = new Member();
        m.setId("member1");
        m.setUsername("회원1");

        //엔티티를 영속 & 1 차캐시에 저장
        em.persist(m);

        //1차 캐시에서 조회
        Member findMember = em.find(Member.class, "member1");
        System.out.println("found : " + findMember);

        //close
        em.close();
        JpaEntityManagerFactory.close();
    }
}
