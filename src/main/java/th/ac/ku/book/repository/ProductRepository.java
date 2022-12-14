package th.ac.ku.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import th.ac.ku.book.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
