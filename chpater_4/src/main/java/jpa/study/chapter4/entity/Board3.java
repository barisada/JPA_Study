package jpa.study.chapter4.entity;

import javax.persistence.*;

@Entity
@TableGenerator(name = "BOARD_SEQ_GENERATOR_TABLE_STRATEGY",
        table = "MY_SEQUENCE",
        pkColumnName = "BOARD_SEQ", allocationSize = 1)
public class Board3 {

    public Board3(String data) {
        this.data = data;
    }

    public Board3() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BOARD_SEQ_GENERATOR_TABLE_STRATEGY")
    private Long id;
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
        return "Board3{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
