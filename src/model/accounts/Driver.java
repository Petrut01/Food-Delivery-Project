package model.accounts;

import model.Car;
import model.Order;

import java.util.ArrayList;

public class Driver extends User{
    private Car car;
    private float salary;
    private ArrayList<Order> deliveries;


    public Driver(String fullname, String email, String password, String phonenumber, String cardnumber) {
        super(fullname, email, password, phonenumber, cardnumber);
        this.deliveries = new ArrayList<Order>();
    }

    public Driver(String fullname, String email, String password, String phonenumber, String cardnumber, Car car) {
        super(fullname, email, password, phonenumber, cardnumber);
        this.car = car;
        this.deliveries = new ArrayList<Order>();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ArrayList<Order> getDeliveries() {
        return deliveries;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setDeliveries(ArrayList<Order> deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "car=" + car +
                ", deliveries=" + deliveries +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
