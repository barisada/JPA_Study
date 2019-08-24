package jpa.study.chapter4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Board4 {
    public Board4() {
    }

    public Board4(String data) {
        this.data = data;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
        return "Board4{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
