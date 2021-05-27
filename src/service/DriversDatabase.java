package service;

import model.Car;
import model.accounts.Driver;


import java.sql.*;
import java.util.ArrayList;

public class DriversDatabase {
    private static DriversDatabase instance = null;
    private final Connection connection;

    private DriversDatabase() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:derby:foodDeliveryDB;create=true");
        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        boolean notFound = true;

        while(results.next()){
            if("drivers".equalsIgnoreCase(results.getString("TABLE_NAME"))){
                notFound = false;
                break;
            }
        }
        if(notFound){
            connection.createStatement()
                    .execute("CREATE TABLE drivers (id varchar(50) primary key, fullName varchar(50)," +
                            "email varchar(30), password varchar(30), phoneNumber varchar(11), cardNumber varchar(16)," +
                            "carBrand varchar(20), carModel varchar(20), carNumberPlate varchar(10), salary varchar(10))");
        }
    }
    public static DriversDatabase getDatabaseInstance2() {
        if (instance == null)
            try {
                instance = new DriversDatabase();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        return instance;
    }

    public Driver createDriver(String[] values) throws SQLException {
        Car car = new Car(values[5], values[6], values[7]);
        float salary = Float.parseFloat(values[8]);
        Driver driver = new Driver(values[0], values[1], values[2], values[3], values[4], car, salary);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO drivers VALUES (?,?,?,?,?,?,?,?,?,?)");
        statement.setString(1,driver.getID());
        for(int i = 2; i <= 10; i++ )
            statement.setString(i,values[i-2]);
        if(statement.executeUpdate() == 1){
            return driver;
        }
        return null;
    }
    public ArrayList<Driver> readDrivers() throws SQLException {
        ResultSet results = connection.createStatement().executeQuery("SELECT * FROM drivers");
        ArrayList<Driver> Drivers = new ArrayList<>();
        while(results.next()){
            Drivers.add(new Driver(results.getString(1), results.getString(2),
                    results.getString(3), results.getString(4), results.getString(5),
                    results.getString(6),new Car(results.getString(7), results.getString(8),
                    results.getString(9)), results.getFloat(10)));
        }
        return Drivers;
    }
    public boolean updateDriver(String id, String[] values) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE drivers SET fullName = ?, email = ?, password = ?, phoneNumber = ?, cardNumber = ?, carBrand = ?, carModel = ?, carNumberPlate = ?, salary = ? WHERE id = ?");
        for (int i = 1; i <= 9; i++)
            statement.setString(i, values[i - 1]);
        statement.setString(10, id);
        return statement.executeUpdate() == 1;
    }

    public boolean deleteDriver(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM drivers WHERE id = ?");
        statement.setString(1, id);
        return statement.executeUpdate() == 1;
    }

}