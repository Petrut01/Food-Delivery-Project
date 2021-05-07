package service;

import model.App;
import model.Product;
import model.Restaurant;
import model.accounts.Client;
import model.accounts.Driver;
import model.accounts.Seller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    private static CSVReader instance = null;
    private Map<String, Path> paths;

    private CSVReader() {
        paths = new HashMap<>();
        paths.put("User", Path.of("data/clients.csv"));
        paths.put("Driver", Path.of("data/drivers.csv"));
        paths.put("Seller", Path.of("data/sellers.csv"));
        paths.put("Products", Path.of("data/products.csv"));

        for (Path path : paths.values()) {
            if (!Files.exists(path)) {
                try {
                    Files.createFile(path);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

    }

    public static CSVReader getInstance() {
        if ( instance == null){
            instance = new CSVReader();
        }
        return instance;
    }

    public void readAll(App app) throws IOException {
            List<Client> clients = getClients();
            for(Client client: clients)
            {
                app.addClient(client);
            }

            List<Driver> drivers = getDrivers();
            for(Driver driver: drivers)
            {
                app.addDriver(driver);
            }

            List<Seller> sellers = getSellers();
            for(Seller seller: sellers)
            {
                app.addSeller(seller);
            }

            List<Product> products = readProducts();
            for(Seller seller : sellers) {
                for (Product product : products)
                    if (seller.getRestaurant().getId().equals(product.getRestaurantId())) {
                        seller.getRestaurant().addProduct(product);

                    }
                app.addRestaurant(seller.getRestaurant());
            }

    }

    public List<Product> readProducts() throws IOException {
        List<Product> products = Files.readAllLines(Paths.get("data/products.csv")).stream()
                .map(line -> new Product(line.split(","))).collect(Collectors.toList());
        return products;
        }



    private List<Seller> getSellers() throws IOException {
        List<Seller> sellers = Files.readAllLines(Paths.get("data/sellers.csv")).stream()
                .map(line -> new Seller(line.split(","))).collect(Collectors.toList());
        return sellers;
    }

    private List<Driver> getDrivers() throws IOException {
        List<Driver> drivers = Files.readAllLines(Paths.get("data/drivers.csv")).stream()
                .map(line -> new Driver(line.split(","))).collect(Collectors.toList());
        return drivers;
    }

    private List<Client> getClients() throws IOException {
        List<Client> clients = Files.readAllLines(Paths.get("data/clients.csv")).stream()
                .map(line -> new Client(line.split(","))).collect(Collectors.toList());
        return clients;
    }

}
