package th.ac.ku.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.book.model.Customer;
import th.ac.ku.book.service.CustomerService;
import th.ac.ku.book.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CreateCustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @RequestMapping("/create-customer")
    public String showCustomers(Model model){
        model.addAttribute("customer", new Customer());
        return "create-customer";
    }

    @PostMapping(value = "/", params = "action=submit")
    public String addCustomer(@Valid Customer customer, Errors errors, Model model) {
        if (errors.hasErrors()){
            return "create-customer";
        }
        System.out.println(customer.getPhoneNumber());
        List<Customer> oldCustomerList = customerService.getAllCustomers();
        for (Customer old: oldCustomerList){
            if (old.getPhoneNumber().matches(customer.getPhoneNumber())){
                return "create-customer";
            }
        }

        customerService.addCustomer(customer);
        return "redirect:/";
    }
}
