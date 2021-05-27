package model.accounts;

import model.Car;
import model.Order;

import java.util.ArrayList;

public class Driver extends User{
    private Car car;
    private float salary;
    private ArrayList<Order> deliveries;


    public Driver(String fullname, String email, String password, String phonenumber, String cardnumber, Car car, float salary) {
        super(fullname, email, password, phonenumber, cardnumber);
        this.car = car;
        this.salary = salary;
        this.deliveries = new ArrayList<Order>();
    }
    public Driver(String id, String fullname, String email, String password, String phonenumber, String cardnumber, Car car, float salary) {
        super(id, fullname, email, password, phonenumber, cardnumber);
        this.car = car;
        this.salary = salary;
        this.deliveries = new ArrayList<Order>();
    }

    public Driver(String fullname, String email, String password, String phonenumber, String cardnumber, Car car) {
        super(fullname, email, password, phonenumber, cardnumber);
        this.car = car;
        this.deliveries = new ArrayList<Order>();
    }

    public Driver(String[] values) {
        super(values[0], values[1], values[2], values[3], values[4], values[5]);
        this.car = new Car(values[6] , values[7], values[8]);
        this.salary = Float.parseFloat(values[9]);
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
