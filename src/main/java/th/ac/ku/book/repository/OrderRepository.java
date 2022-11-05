package th.ac.ku.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import th.ac.ku.book.model.Orders;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query(value = "select o from Orders o where o.status.statusID = 1")
    public List<Orders> findAllNewOrder();

    @Query(value = "select o from Orders o where o.status.statusID = 2 or o.status.statusID = 3 or o.status.statusID = 4 or o.status.statusID = 5 or o.status.statusID = 6")
    public List<Orders> findAllAcceptedOrder();
}
