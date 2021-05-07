package model.accounts;

import model.Order;

import java.util.ArrayList;

public class Client extends User{

    private String address;
    private ArrayList<Order> orders;

    public Client(String fullname, String email, String password, String phonenumber, String cardnumber) {
        super(fullname, email, password, phonenumber, cardnumber);
        this.orders = new ArrayList<Order>();
    }

    public Client(String fullname, String email, String password, String phonenumber, String cardnumber, String address) {
        super(fullname, email, password, phonenumber, cardnumber);
        this.address = address;
        this.orders = new ArrayList<Order>();
    }
    public Client(String id, String fullname, String email, String password, String phonenumber, String cardnumber, String address) {
        super(id, fullname, email, password, phonenumber, cardnumber);
        this.address = address;
        this.orders = new ArrayList<Order>();
    }

    public Client(String[] values) {
        super(values[0], values[1], values[2], values[3], values[4], values[5]);
        this.address = values[6];
        this.orders = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Client{" +
                "address='" + address + '\'' +
                ", orders=" + orders +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }
}
