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

    @PostMapping(value = "/new-order-accept")
    public String acceptOrder(@ModelAttribute("orderStatusFormData") Orders formData, Model model){
        //Set cutstock's order
        Orders currOrder = orderService.findOrderByID(formData.getOrderID());
        List<Long> productIDList = new ArrayList<>();
        List<Integer> productRemainingList = new ArrayList<>();
        List<Double> productPriceIncludeVatList = new ArrayList<>();
        double totalPriceIncludeVat = 0;


        //Find remaining of product and calculate total_price
        for (OrderDetail orderDetail: currOrder.getOrderDetails()){
            int remaining = orderDetail.getProduct().getProductQuantity() - orderDetail.getOrderDetailQuantity();
            productIDList.add(orderDetail.getProduct().getProductID());
            productRemainingList.add(remaining);

            double productPriceIncludeVat = orderDetail.getOrderDetailPrice() * orderDetail.getOrderDetailQuantity();
            productPriceIncludeVatList.add(productPriceIncludeVat);
        }

        for (Double price: productPriceIncludeVatList){
            totalPriceIncludeVat += price;
        }

        totalPriceIncludeVat = (totalPriceIncludeVat * 0.06) + totalPriceIncludeVat;

        //Cut stock
        for (int i = 0; i < productIDList.size(); i++){
            productService.cutStock(productIDList.get(i), productRemainingList.get(i));
        }

        //Change status order (new => approve)
        orderService.changeStatus(formData.getOrderID(), 2);
        orderService.addTotalPriceIncludeVat(formData.getOrderID(), totalPriceIncludeVat);
        return "redirect:/new-order";
    }

    @PostMapping(value = "/new-order-reject")
    public String rejectOrder(@ModelAttribute("orderStatusFormData") Orders formData, Model model){
        orderService.changeStatus(formData.getOrderID(), 0);
        return "redirect:/new-order";
    }
}
