package main;

import model.App;
import model.Product;
import model.Restaurant;
import model.accounts.Admin;
import model.accounts.Client;
import service.CSVReader;
import service.CSVWriter;
import service.LoginService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //Client client = new Client("Albu Dragos","albu@yahoo.com","password","0712345678","1234567890123456", "Ilie Balaci nr. 8");
        Admin admin = new Admin("Ionescu Andrei", "ionescu@gmail.com", "123456", "0722123456", "4324343244423434");
        App app = new App();
        app.addAdmin(admin);
        //app.addClient(client);
        Restaurant restaurant = new Restaurant("Pizza Ibiza","adresa 1");
        Product product1 = new Product(restaurant.getId(), "Margherita",14.99);
        Product product2 = new Product(restaurant.getId(), "Carnivora",23.0);
        restaurant.addProduct(product1);
        restaurant.addProduct(product2);
        app.addRestaurant(restaurant);
        LoginService logInService = new LoginService();
        CSVWriter csvWriter = CSVWriter.getInstance();
        //csvWriter.write(client);
        csvWriter.write(product1);
        csvWriter.write(product2);

        CSVReader csvReader = CSVReader.getInstance();
        csvReader.readAll(app);
        logInService.Main(app);
    }
}

