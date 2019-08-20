package jpa.study.chapter3.persistence_context_ex;

import jpa.study.chapter3.JpaEntityManagerFactory;
import jpa.study.chapter3.Member;

import javax.persistence.EntityManager;

public class GuaranteeEquality_Example {
    public static void main(String[] args) {
        EntityManager em = JpaEntityManagerFactory.getEntityManager();

        //엔티티를 생성한 상태 (비영속)
        Member m = new Member();
        m.setId("member1");
        m.setUsername("회원1");

        //엔티티를 영속 & 1 차캐시에 저장
        em.persist(m);

        Member a = em.find(Member.class, "member1");
        Member b = em.find(Member.class, "member1");

        System.out.println("동일성 체크 : " + (a == b));

        //close
        em.close();
        JpaEntityManagerFactory.close();
    }
}
