package model;

import java.util.ArrayList;
import java.util.UUID;

public class Restaurant {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private String address;
    private ArrayList<Product> menu;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        menu = new ArrayList<Product>();
    }

    public Restaurant() {
        menu = new ArrayList<Product>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Product> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Product> menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", menu=" + menu +
                '}';
    }

    public void addProduct(Product product){
        this.menu.add(product);
    }
}
