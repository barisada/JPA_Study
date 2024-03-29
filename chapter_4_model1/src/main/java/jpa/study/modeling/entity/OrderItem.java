package jpa.study.modeling.entity;

import javax.persistence.*;

@Entity
@Table(name ="ORDER_ITEM")
public class OrderItem {

    @Id @GeneratedValue
    @Column(name="ORDER_ITEM_ID")
    private Long id;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name="ITEM_ID")
    private Long itemId;

    @Column(nullable = false)
    private int orderPrice;

    @Column(nullable = false)
    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", itemId=" + itemId +
                ", orderPrice=" + orderPrice +
                ", count=" + count +
                '}';
    }
}
