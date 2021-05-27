package service;

import model.Product;
import model.Restaurant;


import java.sql.*;
import java.util.ArrayList;

public class ProductsDatabase {
    private static ProductsDatabase instance = null;
    private final Connection connection;

    private ProductsDatabase() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:derby:foodDeliveryDB;create=true");
        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        boolean notFound = true;

        while(results.next()){
            if("products".equalsIgnoreCase(results.getString("TABLE_NAME"))){
                notFound = false;
                break;
            }
        }
        if(notFound){
            connection.createStatement()
                    .execute("CREATE TABLE products (productId varchar(50) primary key, restaurantId varchar(50),"+
                            " name varchar(30), price float(2))");
        }
    }
    public static ProductsDatabase getDatabaseInstance4() {
        if (instance == null)
            try {
                instance = new ProductsDatabase();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        return instance;
    }

    public Product createProduct(String[] values) throws SQLException {
        Product product = new Product(values);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO products VALUES (?,?,?,?)");
        for(int i = 1; i <= 4; i++ )
            statement.setString(i,values[i-1]);
        if(statement.executeUpdate() == 1){
            return product;
        }
        return null;
    }
    public ArrayList<Product> readProducts() throws SQLException {
        ResultSet results = connection.createStatement().executeQuery("SELECT * FROM Products");
        ArrayList<Product> products = new ArrayList<>();
        while(results.next()){
            products.add(new Product(results.getString(1), results.getString(2),
                    results.getString(3), results.getDouble(4)));
        }
        return products;
    }
    public boolean updateProduct(String id, String[] values) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE products SET restaurantId = ?, name = ?, price = ? WHERE productId = ?");
        for (int i = 1; i <= 3; i++)
            statement.setString(i, values[i - 1]);
        statement.setString(4, id);
        return statement.executeUpdate() == 1;
    }

    public boolean deleteProduct(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id = ?");
        statement.setString(1, id);
        return statement.executeUpdate() == 1;
    }

}
