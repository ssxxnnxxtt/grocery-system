package th.ac.ku.book.model;

import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "deliverymethod")
public class DeliveryMethod {
    @Id
    @Column(name = "delivery_method_id")
    private int deliveryMethodID;

    @NotNull
    @Column(name = "delivery_method_detail")
    @Size(max = 20)
    private String deliveryMethodDetail;

    @OneToMany(mappedBy = "deliverymethod")
    private Set<Orders> orders = new HashSet<>();

    public int getDeliveryMethodID() {
        return deliveryMethodID;
    }

    public void setDeliveryMethodID(int deliveryMethodID) {
        this.deliveryMethodID = deliveryMethodID;
    }

    public String getDeliveryMethodDetail() {
        return deliveryMethodDetail;
    }

    public void setDeliveryMethodDetail(String deliveryMethodDetail) {
        this.deliveryMethodDetail = deliveryMethodDetail;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
