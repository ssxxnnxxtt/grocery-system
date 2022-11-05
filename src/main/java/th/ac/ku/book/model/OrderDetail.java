package th.ac.ku.book.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "orderdetail")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderdetail_id")
    private Long orderDetailID;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    @Value("0")
    @Column(name = "orderdetail_quantity")
    private int orderDetailQuantity;

    @NotNull
    @Value("0")
    @Column(name = "orderdetail_price")
    private double orderDetailPrice;

    public Long getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(Long orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOrderDetailQuantity() {
        return orderDetailQuantity;
    }

    public void setOrderDetailQuantity(int orderDetailQuantity) {
        this.orderDetailQuantity = orderDetailQuantity;
    }

    public double getOrderDetailPrice() {
        return orderDetailPrice;
    }

    public void setOrderDetailPrice(double orderDetailPrice) {
        this.orderDetailPrice = orderDetailPrice;
    }
}
