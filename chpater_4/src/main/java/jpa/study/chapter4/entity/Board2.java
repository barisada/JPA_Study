package jpa.study.chapter4.entity;

import javax.persistence.*;

@Entity
@SequenceGenerator(name ="MY_SEQ_GENERATOR",
        sequenceName = "My_Board_Seq",  //매핑할 DB 시퀀스 이름
        initialValue = 1,
        allocationSize = 1 //기본은 50. 50인 이유는 최적화 때문.
)
public class Board2 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_SEQ_GENERATOR")
    private Long id;
    @Lob
    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Board2{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
