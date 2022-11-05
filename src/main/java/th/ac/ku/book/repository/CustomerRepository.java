package th.ac.ku.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.book.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}