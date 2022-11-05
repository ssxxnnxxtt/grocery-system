package th.ac.ku.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.book.model.Customer;
import th.ac.ku.book.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findById(phoneNumber).get();
    }
}
