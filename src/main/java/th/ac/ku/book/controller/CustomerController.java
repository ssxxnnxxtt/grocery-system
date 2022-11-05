package th.ac.ku.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import th.ac.ku.book.model.Customer;
import th.ac.ku.book.model.Product;
import th.ac.ku.book.service.CustomerService;
import th.ac.ku.book.service.ProductService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String showCustomers(Model model){
        List<Customer> customers = customerService.getAllCustomers();
        List<Product> products = productService.getAllProducts();
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        model.addAttribute("customer", new Customer());
        model.addAttribute("searchCustomer", new Customer());
        return "customer-home";
    }

    @PostMapping("/search")
    public String searchCustomer(@ModelAttribute("customerSearchFormData") Customer formData, Model model){
        Customer customer = customerService.getCustomerByPhoneNumber(formData.getPhoneNumber());
        List<Customer> customers = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        customers.add(customer);
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        model.addAttribute("customer", new Customer());
        model.addAttribute("searchCustomer", new Customer());
        return "customer-home";
    }

    @PostMapping(value = "/", params = "action=submit")
    public String addCustomer(@Valid Customer customer, Errors errors, Model model) {
        if (errors.hasErrors()){
            return "redirect:/";
        }
        customerService.addCustomer(customer);
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "redirect:/";
    }

    @PostMapping(value = "/", params = "action=cancel")
    public String cancel(Model model) {
        return "redirect:/";
    }
}
