package th.ac.ku.book.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private Product selectProduct;
    private Integer selectQuantity;

    public Basket(Product selectProduct, Integer selectQuantity){
        this.selectProduct = selectProduct;
        this.selectQuantity = selectQuantity;
    }

    public Product getSelectProduct() {
        return selectProduct;
    }

    public void setSelectProduct(Product selectProduct) {
        this.selectProduct = selectProduct;
    }

    public Integer getSelectQuantity() {
        return selectQuantity;
    }

    public void addQuantity(Integer quantity){
        this.selectQuantity += quantity;
    }

    public void setSelectQuantity(Integer selectQuantity) {
        this.selectQuantity = selectQuantity;
    }
}
