package th.ac.ku.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.book.model.Orders;
import th.ac.ku.book.model.Status;
import th.ac.ku.book.service.OrderService;
import th.ac.ku.book.service.StatusService;

import java.util.List;

@Controller
public class AcceptOrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    StatusService statusService;

    @RequestMapping("/accepted-order")
    public String getAcceptedOrderPage(Model model) {
        List<Orders> ordersList = orderService.findAllAcceptedOrder();
        List<Status> statusList = statusService.getAcceptStatus();
        model.addAttribute("orders", ordersList);
        model.addAttribute("statuses", statusList);
        model.addAttribute("statusOrder", new Orders());
        return "accepted-order";
    }

    /*@PostMapping("/accepted-order")
    public String getStatusID(@ModelAttribute("orderAcceptFormdata") Orders formData, Model model){
        System.out.println(formData.getStatus().getStatusID());
        return "redirect:/accepted-order";
    }*/
}
