package jpa.study.chapter3.persistence_context_ex;

import jpa.study.chapter3.JpaEntityManagerFactory;
import jpa.study.chapter3.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Detach_Example {

    public static void main(String[] args) {
        EntityManager em = JpaEntityManagerFactory.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        Member member = new Member("m1", "test", 123123);

        tx.begin();
        //member 영속
        em.persist(member);

        //member를 영속성 컨텍스트에서 분리. 준영속 상태
        em.detach(member);
        tx.commit();

        Member findMember = em.find(Member.class, "m1");
        System.out.println("found : " +  findMember);

        em.close();
        JpaEntityManagerFactory.close();
    }
}
