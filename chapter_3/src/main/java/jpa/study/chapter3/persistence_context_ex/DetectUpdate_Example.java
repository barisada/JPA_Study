package jpa.study.chapter3.persistence_context_ex;

import jpa.study.chapter3.JpaEntityManagerFactory;
import jpa.study.chapter3.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DetectUpdate_Example {
    public static void main(String[] args) {
        EntityManager em = JpaEntityManagerFactory.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        //init
        initData(tx, em);

        //트랜잭션 시작
        tx.begin();

        //조회
        Member memberA = em.find(Member.class, "memberA");
        System.out.println("before : " + memberA);

        //영속 엔티티 수정
        memberA.setUsername("hi");
        memberA.setAge(10);

        //변경감지(dirty checking)해서 스냅샷 데이토와 다르면 캐시 데이터 수정하고 update SQL 생성
        //update SQL은 모든쿼리를 업데이트하는 쿼리임.
        //org.hibernate.annotations.DynamicUpdate 를 쓰면 변경있는 컬럼만 사용함.

        tx.commit(); //update SQL 실행

        System.out.println("after : " + em.find(Member.class, "memberA"));
        clear(memberA, em, tx);

        em.close();
        JpaEntityManagerFactory.close();
    }

    public static void initData(EntityTransaction tx, EntityManager em){
        tx.begin();
        //영속 후 캐싱
        Member memberA = new Member("memberA", "memba a", 99);
        em.persist(memberA);
        tx.commit();
    }

    public static void clear(Member memberA, EntityManager em, EntityTransaction tx){
        tx.begin();
        em.remove(memberA);
        tx.commit();
    }
}
