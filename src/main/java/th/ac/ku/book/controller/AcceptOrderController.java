package th.ac.ku.book.controller;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.book.model.Orders;
import th.ac.ku.book.model.Status;
import th.ac.ku.book.model.StatusOrder;
import th.ac.ku.book.service.OrderService;
import th.ac.ku.book.service.StatusService;

import java.util.ArrayList;
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
        //List<Status> statusList = statusService.getApprove();
        List<StatusOrder> statusOrderList = new ArrayList<>();


        //List<Status> statusList = new ArrayList<>();
        for (Orders orders: ordersList){
            StatusOrder statusOrder = new StatusOrder();
            statusOrder.setOrders(orders);
            List<Status> statuses = new ArrayList<>();

            switch (orders.getStatus().getStatusID()){
                case 2:
                    statuses = statusService.getApprove();
                    break;
                case 3:
                    statuses = statusService.getPending();
                    break;
                case 4:
                    statuses = statusService.getPreparing();
                    break;
                case 5:
                    statuses = statusService.getDelivering();
                    break;
                case 6:
                    statuses = statusService.getDelivered();
                    break;
            }
            statusOrder.setStatuses(statuses);
            statusOrderList.add(statusOrder);
        }


        model.addAttribute("s", statusOrderList);
        //model.addAttribute("statuses", statusList);
        model.addAttribute("statusOrder", new Orders());
        model.addAttribute("receiptOrder", new Orders());
        return "accepted-order";
    }

    @PostMapping("/accepted-order")
    public String getStatusID(@ModelAttribute("orderStatusFormData") Orders formData, Model model){
        orderService.changeStatus(formData.getOrderID(), formData.getStatus().getStatusID());
        return "redirect:/accepted-order";
    }

    @PostMapping("/accepted-order/receipt")
    public String getReceiptOrder(@ModelAttribute("receiptOrderFormData") Orders formData, Model model){
        Orders receiptOrder = orderService.findOrderByID(formData.getOrderID());
        model.addAttribute("order", receiptOrder);
        return "receipt";
    }
}
