package th.ac.ku.book.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long productID;

    @NotNull
    @Size(min=5, max=30)
    @Column(name="product_name")
    private String productName;

    @NotNull
    @Size(max=130)
    @Column(name="product_image")
    private String productImage;

    @NotNull
    @Size(min=3, max=15)
    @Column(name="product_unit")
    private String productUnit;

    @NotNull
    @Column(name="product_quantity")
    @Value("0")
    private int productQuantity;

    @NotNull
    @Column(name="product_price")
    @Value("0")
    private double productPrice;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new HashSet<>();

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
