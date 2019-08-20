package jpa.study.chapter3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaEntityManagerFactory {
    //공장 만들기 , 비용이 아주 많이 든다
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastudy");

    /**
     * 공장에서 엔티티 매니저 생성, 비용이 거의 안든다.
     * 스레드 세이프하지 않으므로 스레드 간에 절대 공유하면 안된다.
     */
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public static void close(){
        emf.close();
    }
}
