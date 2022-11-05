package th.ac.ku.book.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long orderID;

    @NotNull
    @Column(name="date_release")
    private LocalDateTime dateRelease;

    @NotNull
    @Value("0")
    @Column(name="total_price_include_vat")
    private double totalPriceIncludeVat;

    @ManyToOne
    @JoinColumn(name = "phone_number")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "delivery_method_id")
    private DeliveryMethod deliverymethod;

    @OneToMany(mappedBy = "orders")
    private Set<OrderDetail> orderDetails = new HashSet<>();

    public DeliveryMethod getDeliverymethod() {
        return deliverymethod;
    }

    public void setDeliverymethod(DeliveryMethod deliverymethod) {
        this.deliverymethod = deliverymethod;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }


    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public LocalDateTime getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(LocalDateTime dateRelease) {
        this.dateRelease = dateRelease;
    }

    public double getTotalPriceIncludeVat() {
        return totalPriceIncludeVat;
    }

    public void setTotalPriceIncludeVat(double totalPriceIncludeVat) {
        this.totalPriceIncludeVat = totalPriceIncludeVat;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliverymethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliverymethod = deliveryMethod;
    }
}
