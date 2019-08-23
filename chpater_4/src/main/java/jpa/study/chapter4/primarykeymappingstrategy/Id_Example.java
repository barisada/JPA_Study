package jpa.study.chapter4.primarykeymappingstrategy;

import jpa.study.chapter4.AbstractMain;
import jpa.study.chapter4.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

public class Id_Example extends AbstractMain {
    public static void main(String[] args) {
        Id_Example ex = new Id_Example();
        ex.run();
    }

    public void logic(EntityManager em) {
        String id = "member 1";

        Member member = new Member();
        member.setId(id);   //@ID annotation을 사용하는 전략이므로 직접 입력해야함.
        member.setUsername("wat name");
        member.setAge(2);

        //등록
        em.persist(member);

        //수정
        member.setAge(20);

        //한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        //삭제
        em.remove(member);

    }
}
