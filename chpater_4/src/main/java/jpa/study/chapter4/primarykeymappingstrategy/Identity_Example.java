package jpa.study.chapter4.primarykeymappingstrategy;

import jpa.study.chapter4.AbstractMain;
import jpa.study.chapter4.entity.Board;

import javax.persistence.EntityManager;
import java.util.List;

public class Identity_Example extends AbstractMain {
    public static void main(String[] args) {
        Identity_Example ex = new Identity_Example();
        ex.run();
    }

    @Override
    public void logic(EntityManager em) {

        //Board 는 Identity 전략을 쓰므로 ID가 자동 생성된다.
        //예) MySQL AUTO_INCREMENT
        Board board  = new Board();
        board.setData("A");

        em.persist(board);
        /*
            Identity 전략은 ID를 DB에 저장해야 ID 값을 구할 수 있으므로
            em.persiste()를 호출하는 즉시 INSERT SQL이 데이터베이스에 전달된다.
            따라서 쓰기지연 (Transactional write behind)은 동작하지 않는다.
         */
        System.out.println("board id : " + board.getId());
        System.out.println("board : " + board);

        for(int i = 0; i < 10; i++){
            em.persist(new Board());
        }

        //목록 조회
        List<Board> boards = em.createQuery("select b from Board b", Board.class).getResultList();
        boards.forEach(System.out::println);
        System.out.println("board.size=" + boards.size());

    }
}
