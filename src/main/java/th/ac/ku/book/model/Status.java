package th.ac.ku.book.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name = "status_id")
    private int statusID;

    @NotNull
    @Column(name = "status_detail")
    @Size(max = 20)
    private String statusDetail;

    @OneToMany(mappedBy = "status")
    private Set<Orders> orders = new HashSet<>();

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> statuses) {
        this.orders = statuses;
    }
}
