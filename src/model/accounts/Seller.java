package model.accounts;

import model.Order;
import model.Restaurant;

import java.util.ArrayList;

public class Seller extends User{
    private Restaurant restaurant;
    //private String address;
    private ArrayList<Order> sales;

    public Seller(String fullname, String email, String password, String phonenumber, String cardnumber) {
        super(fullname, email, password, phonenumber, cardnumber);
        this.sales = new ArrayList<Order>();
    }
    public Seller(String fullname, String email, String password, String phonenumber, String cardnumber, Restaurant restaurant) {
        super(fullname, email, password, phonenumber, cardnumber);
        this.restaurant = restaurant;
        //this.address = address;
        this.sales = new ArrayList<Order>();
    }

    @Override
    public String toString() {
        return "Seller{" +
                "restaurant=" + restaurant +
                ", sales=" + sales +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    public ArrayList<Order> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Order> sales) {
        this.sales = sales;
    }
}
