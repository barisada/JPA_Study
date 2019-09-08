package jpa.study.modeling;

import jpa.study.modeling.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class JPAMain {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastudy");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
            //비즈니스 로직
            initData(em);
            //객체 그래프 탐색
            List<Order> orders = em.createQuery("select o from Order o", Order.class).getResultList();
            orders.forEach(order ->{
                Member m = order.getMember();   //주문회원, 객체 참조사용
                System.out.println("주문회원 : " + m);

                List<OrderItem> orderItems = order.getOrderItems();
                orderItems.forEach(orderItem ->{
                    Item item = orderItem.getItem();
                    System.out.println("주문한 상품 : " + item);
                });
            });

            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static void initData(EntityManager em) {
        //멤바
        Member member1 = new Member();
        member1.setName("회원1");
        Member member2 = new Member();
        member2.setName("회원2");

        em.persist(member1);
        em.persist(member2);

        //상품
        Item item1 = new Item();
        item1.setName("물건 1");
        item1.setPrice(138);
        item1.setStockQuantity(99);

        Item item2 = new Item();
        item2.setName("item 2");
        item2.setPrice(3);
        item2.setStockQuantity(999);

        em.persist(item1);
        em.persist(item2);

        //주문
        Order order = new Order();
        order.setMember(member1);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.ORDER);

        em.persist(order);

        //주문 상품
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setItem(item1);
        orderItem1.setOrder(order);
        orderItem1.setCount(2);
        orderItem1.setOrderPrice(item1.getPrice() * orderItem1.getCount());

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setItem(item2);
        orderItem2.setOrder(order);
        orderItem2.setCount(11);
        orderItem2.setOrderPrice(item2.getPrice() * orderItem2.getCount());

        em.persist(orderItem1);
        em.persist(orderItem2);
    }
}
