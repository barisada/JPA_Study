package jpa.study.chapter3.persistence_context_ex;

import jpa.study.chapter3.JpaEntityManagerFactory;
import jpa.study.chapter3.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TransactionalWriteBehind_Example {
    public static void main(String[] args) {
        EntityManager em = JpaEntityManagerFactory.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        //엔티티 매니저는 데이터 변경 시 트랜잭션을 시작해야 한다.
        tx.begin(); //트랜잭션 시작
        Member a = new Member("memberA", "member A", 33);
        Member b = new Member("memberB", "member B", 22);
        em.persist(a);
        em.persist(b);
        //여기까지 Insert SQL을 데이터베이스에 보내지 않는다.

        //커밋하는 순간 데이터베이스에 Insert SQL을 보낸다.
        tx.commit();

        List<Member> list = em.createQuery("select m from Member m ", Member.class).getResultList();
        System.out.println(list);

        //clear
        tx.begin();

        em.remove(a);
        em.remove(b);
        tx.commit();

        em.close();
        JpaEntityManagerFactory.close();
    }
}
