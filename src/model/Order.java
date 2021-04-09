package model;

import model.accounts.Client;
import model.accounts.Driver;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Order {
    private final String id = UUID.randomUUID().toString();
    private String status = "created";
    private Client client;
    private Driver driver;
    private LocalDateTime date;
    private ArrayList<Pair<Product,Integer>> products;

    public Order(Client client, Driver driver) {
        this.client = client;
        this.driver = driver;
        this.products = new ArrayList<Pair<Product,Integer>>();
        this.date = LocalDateTime.now();
    }

    public Order(Client client) {
        this.client = client;
        this.driver = null;
        this.products = new ArrayList<Pair<Product,Integer>>();
        this.date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        String prod = new String();
        prod += "[";
        for (Pair<Product,Integer> p : products){
            prod+= "("+p.getFirst().getName() + " " + p.getSecond() + "),";
        }
        prod += "]";
        if (driver == null)
            return  "Order{" +
                    "user=" + client.getFullname() +
                    ", products=" + prod +
                    ", date=" + date +
                    '}';
        return "Order{" +
                "user=" + client.getFullname() +
                ", driver=" + driver.getFullname() +
                ", products=" + prod +
                ", date=" + date +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ArrayList<Pair<Product, Integer>> getProducts() {
        return products;
    }
    public void addProduct(Product product,int quantity){
        Integer q = quantity;
        Pair<Product,Integer> p = new Pair<>(product,q);
        products.add(p);
        Collections.sort(products);
    }

    public double getTotalPrice(){
        double total = 0;
        for (Pair<Product,Integer> p: products){
            total += p.getSecond()*p.getFirst().getPrice();
        }
        return total;
    }

}
