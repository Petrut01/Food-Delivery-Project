package service;

import model.App;
import model.accounts.Admin;
import model.accounts.Client;
import model.accounts.Driver;
import model.accounts.Seller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminService {

    private final Scanner scanner = new Scanner(System.in);
    private final AuditService audit = AuditService.getInstance();
    private final SellersDatabase sellersDatabase = SellersDatabase.getDatabaseInstance3();
    private final DriversDatabase driversDatabase = DriversDatabase.getDatabaseInstance2();
    private final ClientsDatabase clientsDatabase = ClientsDatabase.getDatabaseInstance1();

    public void Main(App app, Admin admin) throws SQLException {

        audit.write("Login - "+admin.getFullname());
        System.out.println("\nLogged in as Admin");
        System.out.println(admin);
        for(;;){
            System.out.println("Select an option");
            System.out.println("1. Show Admin menu");
            System.out.println("2. Show Client Menu");
            System.out.println("0. Exit");
            int option = scanner.nextInt();
            if(option == 0){
                break;
            }else if(option == 2){
                ClientService clientService = new ClientService();
                clientService.Main(app,admin.getClient_admin());
            }else if(option != 1){
                System.out.println("Invalid option");
            }else{
                for(;;){
                    System.out.println("Select an option");
                    System.out.println("1. Display all clients");
                    System.out.println("2. Display all drivers");
                    System.out.println("3. Display all sellers");
                    System.out.println("4. Display all restaurants");
                    System.out.println("5. Display all orders");
                    System.out.println("0. Exit");
                    int option2 = scanner.nextInt();
                    if(option2==0){
                        System.out.println("Exiting...");
                        break;
                    }
                    switch (option2){
                        case 1:
                            audit.write("Show Clients - "+admin.getFullname());
                            //System.out.println(app.getClients());
                            ArrayList<Client> clients = clientsDatabase.readClients();
                            System.out.println(clients);
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        case 2:
                            audit.write("Show Drivers - "+admin.getFullname());
                            //System.out.println(app.getDrivers());
                            ArrayList<Driver> drivers = driversDatabase.readDrivers();
                            System.out.println(drivers);
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        case 3:
                            audit.write("Show Sellers - "+admin.getFullname());
                            //System.out.println(app.getSellers());
                            ArrayList<Seller> sellers = sellersDatabase.readSellers();
                            System.out.println(sellers);
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        case 4:
                            audit.write("Show Restaurants - "+admin.getFullname());
                            System.out.println(app.getRestaurants());
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        case 5:
                            audit.write("Show Orders - "+admin.getFullname());
                            System.out.println(app.getOrders());
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                        default:
                            System.out.println("Invalid option");
                            scanner.nextLine();
                            scanner.nextLine();
                            break;
                    }
                }
            }
        }
    }
}
