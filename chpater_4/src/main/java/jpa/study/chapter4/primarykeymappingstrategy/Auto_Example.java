package jpa.study.chapter4.primarykeymappingstrategy;

import jpa.study.chapter4.AbstractMain;
import jpa.study.chapter4.entity.Board3;
import jpa.study.chapter4.entity.Board4;

import javax.persistence.EntityManager;
import java.util.List;

public class Auto_Example extends AbstractMain {

    public static void main(String[] args) {
        Auto_Example ex = new Auto_Example();
        ex.run();
    }

    @Override
    public void logic(EntityManager em) {
        //Auto 전략을 사용하므로 id값은 자동 할당된다.
        Board4 board  = new Board4();
        board.setData("A");

        //Auto 전략은 선택한 DB dialect에 따라 Identity, Sequence, Table 전략 중 하나를 선택한다.
        em.persist(board);

        System.out.println("board id : " + board.getId());
        System.out.println("board : " + board);

        for(int i = 0; i < 5; i++){
            em.persist(new Board4((String.valueOf((char) (i + 40)))));
        }

        //목록 조회
        List<Board4> boards = em.createQuery("select b from Board4 b", Board4.class).getResultList();
        boards.forEach(System.out::println);
        System.out.println("Board4.size=" + boards.size());
    }
}
