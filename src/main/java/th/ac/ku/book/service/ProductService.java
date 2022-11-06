package th.ac.ku.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.book.model.Product;
import th.ac.ku.book.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByID(Long productID){
        return productRepository.findById(productID).get();
    }

    public void cutStock(Long productID, Integer remaining){
        Product product = productRepository.findById(productID).get();
        product.setProductQuantity(remaining);
        productRepository.save(product);
    }
}
