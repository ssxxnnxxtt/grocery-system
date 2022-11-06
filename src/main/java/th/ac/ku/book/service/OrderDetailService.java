package th.ac.ku.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.book.model.OrderDetail;
import th.ac.ku.book.repository.OrderDetailRepository;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    public void addOrderDetails(OrderDetail orderDetail){
        orderDetailRepository.save(orderDetail);
    }
}
