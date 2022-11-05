package th.ac.ku.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.book.model.Orders;
import th.ac.ku.book.service.OrderService;

import java.util.List;

@Controller
public class AcceptOrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/accepted-order")
    public String getAcceptedOrderPage(Model model) {
        List<Orders> ordersList = orderService.findAllAcceptedOrder();
        model.addAttribute("orders", ordersList);
        return "accepted-order";
    }
}
