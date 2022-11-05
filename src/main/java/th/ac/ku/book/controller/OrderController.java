package th.ac.ku.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.book.model.OrderDetail;
import th.ac.ku.book.model.Orders;
import th.ac.ku.book.service.OrderService;
import th.ac.ku.book.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @RequestMapping("/new-order")
    public String getNewOrderPage(Model model) {
        List<Orders> ordersList = orderService.findAllNewOrder();
        model.addAttribute("orders", ordersList);
        model.addAttribute("acceptOrder", new Orders());
        return "new-order";
    }

    @PostMapping("/new-order")
    public String getAcceptOrderID(@ModelAttribute("orderAcceptFormdata") Orders formData, Model model){
        //Set cutstock's order
        Orders currOrder = orderService.findOrderByID(formData.getOrderID());
        List<Long> productIDList = new ArrayList<>();
        List<Integer> productRemainingList = new ArrayList<>();

        //Find remaining of product
        for (OrderDetail orderDetail: currOrder.getOrderDetails()){
            int remaining = orderDetail.getProduct().getProductQuantity() - orderDetail.getOrderDetailQuantity();
            productIDList.add(orderDetail.getProduct().getProductID());
            productRemainingList.add(remaining);
        }

        //Cut stock
        for (int i = 0; i < productIDList.size(); i++){
            productService.cutStock(productIDList.get(i), productRemainingList.get(i));
        }

        //Change status order (new => approve)
        orderService.changeToApprovedStatus(formData.getOrderID());
        return "redirect:/new-order";
    }
}
