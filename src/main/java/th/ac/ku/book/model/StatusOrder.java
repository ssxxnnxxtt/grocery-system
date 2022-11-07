package th.ac.ku.book.model;

import java.util.ArrayList;
import java.util.List;

public class StatusOrder {
    private Orders orders;
    private int statusColor;
    private List<Status> statuses;

    public void setOrders(Orders orders){
        this.orders = orders;
    }

    public void setStatusColor(){
        List<Integer> statuses = new ArrayList<>();
        for (OrderDetail detail: orders.getOrderDetails()){
            if (detail.getProduct().getProductQuantity() < detail.getOrderDetailQuantity()){
                statuses.add(-1);
            } else {
                statuses.add(1);
            }
        }

        //Set green
        for (int i = 0; i < statuses.size(); i++){
            if (statuses.get(i) == -1) break;
            if (i == statuses.size() - 1){
                this.statusColor = 1;
                return;
            }
        }

        //Set red
        for (int i = 0; i < statuses.size(); i++){
            if (statuses.get(i) == 1) break;
            if (i == statuses.size() - 1){
                this.statusColor = 3;
                return;
            }
        }

        //Set yellow
        this.statusColor = 2;
    }

    public void setStatuses(List<Status> statuses){
        this.statuses = statuses;
    }

    public Orders getOrders() {
        return orders;
    }

    public int getStatusColor() {
        return statusColor;
    }

    public List<Status> getStatuses(){
        return statuses;
    }

    /*
    statusColor(green) = 1
    statusColor(yellow) = 2
    statusColor(red) = 3
     */
}
