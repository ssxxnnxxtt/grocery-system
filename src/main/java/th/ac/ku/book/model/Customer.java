package th.ac.ku.book.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Pattern(regexp = "[0][1-9][0-9]{8}", message = "* Please enter a phone number starting with 0 only.")
    @Size(min=10, max=10, message = "* Please fill 10 digits in your phone number.")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = "[ก-๛]+[ ][ก-๛]+", message = "* Please enter your first and last name in Thai language " +
            "and fill out completely")
    @Column(name = "customer_name")
    private String customerName;

    @Size(max=50, message = "* Characters over than the limit (50 characters)")
    @Column(name = "address")
    private String address;

    @Size(max=20, message = "* Characters over than the limit (20 characters)")
    @Column(name = "line_id")
    private String lineID;

    @OneToMany(mappedBy = "customer")
    private Set<Orders> orders = new HashSet<>();

    public Customer(){}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLineID() {
        return lineID;
    }

    public void setLineID(String lineID) {
        this.lineID = lineID;
    }

    public Set<Orders> customers() {
        return orders;
    }

    public void setCustomers(Set<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", lineID='" + lineID + '\'' +
                '}';
    }
}