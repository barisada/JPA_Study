package jpa.study.chapter3.persistence_context_ex;

import jpa.study.chapter3.JpaEntityManagerFactory;
import jpa.study.chapter3.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Merge_Example {
    public static void main(String[] args) {
        Member m = createMember("memberA", "mema aay", 55);

        //영속 상태에서 변경
        m.setUsername("member A");
        mergeMember(m);

        JpaEntityManagerFactory.close();
    }

    private static Member createMember(String id, String name, int age) {
        EntityManager em = JpaEntityManagerFactory.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Member member = new Member(id, name, age);
        em.persist(member);
        tx.commit();

        em.close();     //영속성 컨텍스트1 종료

        //member는 준영속 상태가 된다
        return member;
    }

    private static void mergeMember(Member m) {
        EntityManager em = JpaEntityManagerFactory.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Member mergedMember = em.merge(m);  //병합하는순간 변경내용 적용. 준영속 엔티티를 merge하면 다시 영속 엔티티가 됨.
        tx.commit();

        System.out.println("준영속 : "  + m);
        System.out.println("영속 상태 : " + mergedMember);
        System.out.println("em contains 준영속 ? : " + em.contains(m));
        System.out.println("em contains 영속 ? : " + em.contains(mergedMember));

        tx.begin();
        em.remove(mergedMember);
        tx.commit();
        em.close();
    }
}
