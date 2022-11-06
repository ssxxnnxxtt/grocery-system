package th.ac.ku.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.book.model.*;
import th.ac.ku.book.service.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DeliveryMethodService deliveryMethodService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    private Customer selectCustomer;
    private List<Basket> basketCollection = new ArrayList<>();

    @GetMapping("/")
    public String showCustomers(Model model){
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
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
        model.addAttribute("selectProductCustomer", new Customer());
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

    //Start select_product page
    @GetMapping(value = "/select_product")
    public String getSelectProductPage(@ModelAttribute("customerSearchFormData") Customer formData, Model model){
        //Clear basket list
        selectCustomer = null;
        basketCollection.clear();

        selectCustomer = customerService.getCustomerByPhoneNumber(formData.getPhoneNumber());
        model.addAttribute("selectCustomer", selectCustomer);

        List<Product> products = productService.getAllProducts();
        List<DeliveryMethod> deliveryMethods = deliveryMethodService.getAllDeliveryMethods();

        Orders orders = new Orders();
        long id = 0;
        orders.setOrderID(id);

        model.addAttribute("products", products);
        model.addAttribute("addProduct", new Product());
        model.addAttribute("baskets", basketCollection);
        model.addAttribute("deliveryMethods", deliveryMethods);
        model.addAttribute("createOrder", new Orders());
        model.addAttribute("currOrder", orders);
        return "select-product";
    }

    @PostMapping(value = "/select_product")
    public String clickViewProduct(@ModelAttribute("selectProductFormData") Product formData, Model model){
        //Manage add product to basket list
        Product selectProduct = productService.getProductByID(formData.getProductID());

        Basket currBasket = new Basket(selectProduct, formData.getProductQuantity());
        basketCollection.add(currBasket);

        List<Product> products = productService.getAllProducts();
        List<DeliveryMethod> deliveryMethods = deliveryMethodService.getAllDeliveryMethods();

        Orders orders = new Orders();
        long id = 0;
        orders.setOrderID(id);

        model.addAttribute("products", products);
        model.addAttribute("selectCustomer", selectCustomer);
        model.addAttribute("baskets", basketCollection);
        model.addAttribute("deliveryMethods", deliveryMethods);
        model.addAttribute("createOrder", new Orders());
        model.addAttribute("currOrder", orders);
        return "select-product";
    }

    @PostMapping(value = "/", name = "orderID")
    public String createOrder(@ModelAttribute("createOrderFormData") Orders formData){
        Orders orders = new Orders();
        LocalDateTime now = LocalDateTime.now();
        orders.setDateRelease(now);
        orders.setCustomer(selectCustomer);

        Status status = new Status();
        status.setStatusID(1);
        orders.setStatus(status);

        int createDeliveryMethodID = formData.getDeliveryMethod().getDeliveryMethodID();
        DeliveryMethod deliveryMethod = new DeliveryMethod();
        deliveryMethod.setDeliveryMethodID(createDeliveryMethodID);
        orders.setDeliveryMethod(deliveryMethod);

        //Save order to database
        orderService.addOrder(orders);

        Orders saveOrder = orderService.getOrderFromDateTime(now);

        for (int i = 0; i < basketCollection.size(); i++){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrders(saveOrder);
            orderDetail.setProduct(basketCollection.get(i).getSelectProduct());
            orderDetail.setOrderDetailQuantity(basketCollection.get(i).getSelectQuantity());
            orderDetail.setOrderDetailPrice(basketCollection.get(i).getSelectProduct().getProductPrice());
            orderDetailService.addOrderDetails(orderDetail);
        }

        return "redirect:/";
    }
}
