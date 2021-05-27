package service;

import model.App;
import model.Car;
import model.Restaurant;
import model.accounts.*;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginService {

    private final AuditService audit = AuditService.getInstance();
    private final Scanner scanner = new Scanner(System.in);
    private final SellersDatabase sellersDatabase = SellersDatabase.getDatabaseInstance3();
    private final DriversDatabase driversDatabase = DriversDatabase.getDatabaseInstance2();
    private final ClientsDatabase clientsDatabase = ClientsDatabase.getDatabaseInstance1();
    private final CSVWriter csvWriter = CSVWriter.getInstance();

    public void Main(App app) throws SQLException {
        int option;
        for (; ; ) {
            System.out.println("Select an option");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            System.out.println("0. Exit App");
            option = scanner.nextInt();

            switch (option) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    for (; ; ) {
                        System.out.println("Email");
                        String email = scanner.next();
                        System.out.println("Password");
                        String password = scanner.next();
                        User user = login(email, password, app);
                        if (user == null) {
                            System.out.println("Invalid credentials");
                            System.out.println("1. Try again");
                            System.out.println("2. Back");
                            int option2 = scanner.nextInt();
                            if (option2 == 2){
                                break;
                            }else if(option2 != 1){
                                System.out.println("Invalid optin - going back...");
                                break;
                            }

                        } else if (user instanceof Admin) {
//                            System.out.println("Logged in as Admin");

                            AdminService adminService = new AdminService();
                            adminService.Main(app, (Admin)user);
                            break;

                        } else if (user instanceof Driver) {
//                            System.out.println("Logged in as Driver");

                            DriverService driverService = new DriverService();
                            driverService.Main(app, (Driver)user);
                            break;

                        } else if (user instanceof Seller) {
//                            System.out.println("Logged in as Seller");
                            SellerService sellerService = new SellerService();
                            sellerService.Main(app, (Seller) user);
                            break;

                        } else if (user instanceof Client){
                            ClientService clientService = new ClientService();
                            clientService.Main(app, (Client) user);
                            break;
                        }
                    }
                    break;
                case 2:
                    int option2;
                    System.out.println("1. Sign up as a client");
                    System.out.println("2. Sign up as a driver");
                    System.out.println("3. Sign up as a seller");
                    System.out.println("0. Back");
                    option2 = scanner.nextInt();
                    if (option2 >= 1 && option2 <= 3) {
                        signup(option2, app);
                    }

            }
        }
    }

    private User login(String email, String password, App app) {
        for (User user : app.getUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void signup(int type, App app) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Full Name");
        String fullname = scanner.nextLine();
        System.out.println("Password");
        String password = scanner.next();
        System.out.println("Email");
        String email = scanner.next();
        System.out.println("Phone number");
        String phonenumber = scanner.next();
        System.out.println("Address");
        String address = scanner.next();
        System.out.println("Card Number");
        String cardnumber = scanner.next();

        switch (type) {
            case 1: //client
                //Client client = new Client(fullname, email, password, phonenumber, cardnumber, address);
                String[] values = new String[]{fullname, email, password, phonenumber, cardnumber, address};
                Client client = clientsDatabase.createClient(values);
                app.addClient(client);
                csvWriter.write(client);
                audit.write("SignUp - "+client.getFullname());
                break;
            case 2: //driver
                System.out.println("Enter your car information");
                System.out.println("Brand");
                String brand = scanner.next();
                System.out.println("Model");
                String model = scanner.next();
                System.out.println("NumberPlate");
                String numberPlate = scanner.next();
                System.out.println("Salary");
                String salary = scanner.next();
                //Car car = new Car(brand, model, numberplate);
                //Driver driver = new Driver(fullname, email, password, phonenumber, cardnumber, car);
                String[] valuesDriver = new String[]{fullname, email, password, phonenumber, cardnumber, brand, model, numberPlate,salary};
                Driver driver = driversDatabase.createDriver(valuesDriver);
                app.addDriver(driver);
                csvWriter.write(driver);
                audit.write("SignUp - "+driver.getFullname());
                break;
            case 3: //seller
                System.out.println("Enter your restaurant information");
                System.out.println("Name");
                String name = scanner.next();
                System.out.println("Address");
                String raddress = scanner.next();
                //Restaurant restaurant = new Restaurant(name,raddress);
                //Seller seller = new Seller(fullname, email, password, phonenumber, cardnumber, restaurant);
                String[] valuesSeller = new String[]{fullname, email, password, phonenumber, cardnumber, name, raddress};
                Seller seller = sellersDatabase.createSeller(valuesSeller);
                app.addSeller(seller);
                csvWriter.write(seller);
                audit.write("SignUp - "+seller.getFullname());
                break;
        }
    }
}
