package model;

import model.accounts.*;

import java.util.ArrayList;
import java.util.HashSet;

public class App {
    private static App instance = null;

    private HashSet<Restaurant> restaurants;
    private ArrayList<Client> clients;
    private ArrayList<Driver> drivers;
    private ArrayList<Seller> sellers;
    private ArrayList<Admin> admins;
    private ArrayList<Order> orders;
    private ArrayList<User> users;


    public App() {
        this.restaurants = new HashSet<>();
        this.clients = new ArrayList<Client>();
        this.drivers = new ArrayList<Driver>();
        this.sellers = new ArrayList<Seller>();
        this.admins = new ArrayList<Admin>();
        this.orders = new ArrayList<Order>();
        this.users = new ArrayList<User>();
        Admin admin = new Admin("Admin Admin", "admin@admin.ro", "admin", "0712345678", "1234 5678 9012 3456");
        addAdmin(admin);
    }
    public static App App() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    public HashSet<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(HashSet<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(ArrayList<Seller> sellers) {
        this.sellers = sellers;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addClient(Client client) {
        this.clients.add(client);
        this.users.add(client);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addDriver(Driver driver) {
        this.drivers.add(driver);
        this.users.add(driver);
    }

    public void addSeller(Seller seller) {
        this.sellers.add(seller);
        this.users.add(seller);
        this.restaurants.add(seller.getRestaurant());
    }

    public void addAdmin(Admin admin) {
        this.admins.add(admin);
        this.users.add(admin);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    public void addRestaurant(Restaurant restaurant){
        this.restaurants.add(restaurant);
    }

}
