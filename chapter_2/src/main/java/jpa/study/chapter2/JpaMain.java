package jpa.study.chapter2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastudy");

        //엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        //트랜잭션 - 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); //트랜잭션 시작
            logic(em);  //비지니스로직 실행
            tx.commit();    //트랜잭션 커밋
        } catch (Exception e){
            tx.rollback();  //트랜잭션 롤백
        } finally {
            em.close(); //엔티티매니저 종료
        }
        emf.close();    //d앤티티 매니저 팩토리 종료
    }

    //비지니스 로직
    private static void logic(EntityManager em) {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("길동");
        member.setAge(2);

        //등록
        em.persist(member);

        //수정
        member.setAge(20);

        //한건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println(findMember);

        //목록조회
        List<Member> members = em.createQuery("select m from Member m ", Member.class).getResultList();
        System.out.println("member size : " + members.size());

        //삭제
        em.remove(member);
    }
}
