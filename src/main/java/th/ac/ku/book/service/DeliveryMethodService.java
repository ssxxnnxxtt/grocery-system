package th.ac.ku.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.book.model.DeliveryMethod;
import th.ac.ku.book.repository.DeliveryMethodRepository;

import java.util.List;

@Service
public class DeliveryMethodService {
    @Autowired
    DeliveryMethodRepository deliveryMethodRepository;

    public List<DeliveryMethod> getAllDeliveryMethods(){
        return deliveryMethodRepository.findAll();
    }
}
