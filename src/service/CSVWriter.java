package service;

import model.Ingredient;
import model.Product;
import model.accounts.Client;
import model.accounts.Driver;
import model.accounts.Seller;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class CSVWriter extends AuditService {
    private static CSVWriter instance = null;
    Map<String, Path> paths;

    private CSVWriter() {
        super();
        paths = new HashMap<>();
        paths.put("Client", Path.of("data/clients.csv"));
        paths.put("Driver", Path.of("data/drivers.csv"));
        paths.put("Seller", Path.of("data/sellers.csv"));
        //paths.put("Restaurant", Path.of("data/restaurants.csv"));
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
    public static CSVWriter getInstance() {
        if (instance == null) {
            instance = new CSVWriter();
        }
        return instance;
    }

    public void write(Client client) {
        write("Writing user");
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(paths.get("Client"), StandardOpenOption.APPEND);
            String output = client.getID() + "," +
                    client.getFullname() + "," +
                    client.getEmail() + "," +
                    client.getPassword() + "," +
                    client.getPhonenumber() + "," +
                    client.getCardnumber() + "," +
                    client.getAddress();
            bufferedWriter.write(output + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void write(Driver driver) {
        write("Writing user");
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(paths.get("Driver"), StandardOpenOption.APPEND);
            String output = driver.getID() + "," +
                    driver.getFullname() + "," +
                    driver.getEmail() + "," +
                    driver.getPassword() + "," +
                    driver.getPhonenumber() + "," +
                    driver.getCardnumber() + "," +
                    driver.getCar().getBrand() + "," +
                    driver.getCar().getModel() + "," +
                    driver.getCar().getNumberplate() ;
            bufferedWriter.write(output + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void write(Seller seller) {
        write("Writing user");
        try {

            BufferedWriter bufferedWriter = Files.newBufferedWriter(paths.get("Seller"), StandardOpenOption.APPEND);
            String menu = "";
            if(seller.getRestaurant().getMenu().size()>0) {
                for (Product product : seller.getRestaurant().getMenu()) {
                    menu += product.getName() + ";";
                }
                menu = menu.substring(0, menu.length() - 1);
            }
            String output = seller.getID() + "," +
                        seller.getFullname() + "," +
                        seller.getEmail() + "," +
                        seller.getPassword() + "," +
                        seller.getPhonenumber() + "," +
                        seller.getCardnumber() + "," +
                        seller.getRestaurant().getId() + "," +
                        seller.getRestaurant().getName() + "," +
                        seller.getRestaurant().getAddress();
            if(menu.length()>0) {
                output += "," + menu;
            }

            bufferedWriter.write(output + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void write(Product product) {
        write("Writing product");
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(paths.get("Products"), StandardOpenOption.APPEND);
            String ingredients = "";
            if(product.getIngredients().size() > 0){
                for (Ingredient ingredient : product.getIngredients()) {
                    ingredients += ingredient.toString() + ",";
                }
                ingredients = ingredients.substring(0, ingredients.length() - 1);
            }

            if (ingredients.length()>0){
                String output = product.getProductId() + "," +
                        product.getRestaurantId() + "," +
                        product.getName() + "," +
                        product.getPrice() + "," +
                        ingredients;
                bufferedWriter.write(output + "\n");
            }
            else {
                String output = product.getProductId() + "," +
                        product.getRestaurantId() + "," +
                        product.getName() + "," +
                        product.getPrice();
                bufferedWriter.write(output + "\n");
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
