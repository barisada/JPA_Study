package jpa.study.chapter4;

import jpa.study.chapter4.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaMain extends AbstractMain{
    public static void main(String[] args) {
        JpaMain main = new JpaMain();
        main.run();
    }

    @Override
    public void logic(EntityManager em) {

        String id = "member 1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("고고씽");
        member.setAge(2);

        //등록
        em.persist(member);

        //수정
        member.setAge(20);

        //한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());
        System.out.println("find member info all : " + findMember);

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        //삭제
        em.remove(member);

    }
}
