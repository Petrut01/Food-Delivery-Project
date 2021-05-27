package service;

import model.Restaurant;
import model.accounts.Seller;

import java.sql.*;
import java.util.ArrayList;

public class SellersDatabase {
    private static SellersDatabase instance = null;
    private final Connection connection;

    private SellersDatabase() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:derby:foodDeliveryDB;create=true");
        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        boolean notFound = true;

        while(results.next()){
            if("sellers".equalsIgnoreCase(results.getString("TABLE_NAME"))){
                notFound = false;
                break;
            }
        }
        if(notFound){
            connection.createStatement()
                    .execute("CREATE TABLE sellers (id varchar(50) primary key, fullName varchar(50)," +
                            "email varchar(30), password varchar(30), phoneNumber varchar(11), cardNumber varchar(16)," +
                            "restaurantId varchar(50), restaurantName varchar(20), restaurantAddress varchar(30))");
        }
    }
    public static SellersDatabase getDatabaseInstance3() {
        if (instance == null)
            try {
                instance = new SellersDatabase();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        return instance;
    }

    public Seller createSeller(String[] values) throws SQLException {
        Restaurant restaurant = new Restaurant(values[5],values[6]);
        Seller seller = new Seller(values[0],values[1], values[2], values[3], values[4], restaurant);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Sellers VALUES (?,?,?,?,?,?,?,?,?)");
        statement.setString(1,seller.getID());
        for(int i = 2; i <= 6; i++ )
            statement.setString(i,values[i-2]);
        statement.setString(7,seller.getRestaurant().getId());
        for(int i = 8; i <= 9; i++ )
            statement.setString(i,values[i-3]);
        if(statement.executeUpdate() == 1){
            return seller;
        }
        return null;
    }
    public ArrayList<Seller> readSellers() throws SQLException {
        ResultSet results = connection.createStatement().executeQuery("SELECT * FROM sellers");
        ArrayList<Seller> sellers = new ArrayList<>();
        while(results.next()){
            sellers.add(new Seller(results.getString(1), results.getString(2),
                    results.getString(3), results.getString(4), results.getString(5),
                    results.getString(6),new Restaurant(results.getString(7), results.getString(8),
                    results.getString(9))));
        }
        return sellers;
    }
    public boolean updateSeller(String id, String[] values) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE sellers SET fullName = ?, email = ?, password = ?, phoneNumber = ?, cardNumber = ?, restaurantId = ?, restaurantName = ?, restaurantAddress = ? WHERE id = ?");
        for (int i = 1; i <= 8; i++)
            statement.setString(i, values[i - 1]);
        statement.setString(9, id);
        return statement.executeUpdate() == 1;
    }

    public boolean deleteSeller(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM sellers WHERE id = ?");
        statement.setString(1, id);
        return statement.executeUpdate() == 1;
    }

}
