package jpa.study.chapter4.primarykeymappingstrategy;

import jpa.study.chapter4.AbstractMain;
import jpa.study.chapter4.entity.Board3;

import javax.persistence.EntityManager;
import java.util.List;

public class Table_example extends AbstractMain {

    public static void main(String[] args) {
        Table_example ex = new Table_example();
        ex.run();
    }

    @Override
    public void logic(EntityManager em) {
        //Table 전략을 사용하므로 id값은 자동 할당된다.
        Board3 board  = new Board3();
        board.setData("A");

        //@TableGenerator로 생성된 시퀀스테이블 조회를 해서 ID값을 받아오고 그후 persistence context에 엔티티를 저장한다.
        //Identity 전략과 달리 쓰기 지연(transactional write behind)이 가능
        //Sequence와 달리 시퀀스테이블 값 증가를 위해 update 쿼리를 한번 더 사용한다.
        em.persist(board);

        System.out.println("board id : " + board.getId());
        System.out.println("board : " + board);

        for(int i = 0; i < 5; i++){
            em.persist(new Board3((String.valueOf((char) (i + 80)))));
        }

        //목록 조회
        List<Board3> boards = em.createQuery("select b from Board3 b", Board3.class).getResultList();
        boards.forEach(System.out::println);
        System.out.println("Board3.size=" + boards.size());
    }
}
