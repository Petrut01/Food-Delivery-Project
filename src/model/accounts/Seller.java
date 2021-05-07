package model.accounts;

import model.Ingredient;
import model.Order;
import model.Restaurant;

import java.util.ArrayList;

public class Seller extends User{
    private Restaurant restaurant;
    private ArrayList<Order> sales;

    public Seller(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.sales = new ArrayList<Order>();
    }

    public Seller(String fullname, String email, String password, String phonenumber, String cardnumber) {
        super(fullname, email, password, phonenumber, cardnumber);
        this.sales = new ArrayList<Order>();
    }
    public Seller(String fullname, String email, String password, String phonenumber, String cardnumber, Restaurant restaurant) {
        super(fullname, email, password, phonenumber, cardnumber);
        this.restaurant = restaurant;
        this.sales = new ArrayList<Order>();
    }

    public Seller(String[] values){
        super(values[0], values[1], values[2], values[3], values[4], values[5]);
        this.restaurant = new Restaurant(values[6], values[7], values[8]);

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
