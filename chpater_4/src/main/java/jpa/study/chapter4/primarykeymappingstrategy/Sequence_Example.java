package jpa.study.chapter4.primarykeymappingstrategy;

import jpa.study.chapter4.AbstractMain;
import jpa.study.chapter4.entity.Board2;

import javax.persistence.EntityManager;
import java.util.List;

public class Sequence_Example extends AbstractMain {

    public static void main(String[] args) {
        Sequence_Example ex = new Sequence_Example();
        ex.run();
    }

    @Override
    public void logic(EntityManager em) {

        //Sequence 전략을 사용하므로 id값은 자동 할당된다.
        Board2 board  = new Board2();
        board.setData("A");

        //먼저 DB 시퀀스 조회를 해서 ID값을 받아오고 그후 persistence context에 엔티티를 저장한다.
        //Identity 전략과 달리 쓰기 지연(transactional write behind)이 가능
        em.persist(board);

        System.out.println("board id : " + board.getId());
        System.out.println("board : " + board);

        for(int i = 0; i < 5; i++){
            em.persist(new Board2());
        }

        //목록 조회
        List<Board2> boards = em.createQuery("select b from Board2 b", Board2.class).getResultList();
        boards.forEach(System.out::println);
        System.out.println("Board2.size=" + boards.size());
    }
}
