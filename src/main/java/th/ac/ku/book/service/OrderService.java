package th.ac.ku.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import th.ac.ku.book.model.Orders;
import th.ac.ku.book.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Orders> findAllNewOrder(){
        return orderRepository.findAllNewOrder();
    }

    public List<Orders> findAllAcceptedOrder(){
        return orderRepository.findAllAcceptedOrder();
    }

}
