package th.ac.ku.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import th.ac.ku.book.model.Orders;
import th.ac.ku.book.model.Status;
import th.ac.ku.book.repository.OrderRepository;

import java.time.LocalDateTime;
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

    public Orders findOrderByID(Long orderID) {return orderRepository.findById(orderID).get(); }

    public void changeStatus(Long orderID, Integer changeStatusID){
        Orders orders = orderRepository.findById(orderID).get();
        Status status = new Status();
        status.setStatusID(changeStatusID);
        orders.setStatus(status);
        orderRepository.save(orders);
    }

    public Orders getOrderFromDateTime(LocalDateTime localDateTime){
        return orderRepository.getOrderFromDateTime(localDateTime);
    }

    public void addOrder(Orders orders){
        orderRepository.save(orders);
    }

    public void addTotalPriceIncludeVat(Long orderID, Double price){
        Orders orders = orderRepository.findById(orderID).get();
        orders.setTotalPriceIncludeVat(price);
        orderRepository.save(orders);
    }
}
