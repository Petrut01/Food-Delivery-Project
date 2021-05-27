package service;

import model.accounts.Client;

import java.sql.*;
import java.util.ArrayList;

public class ClientsDatabase {
    private static ClientsDatabase instance = null;
    private final Connection connection;

    private ClientsDatabase() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:derby:foodDeliveryDB;create=true");
        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        boolean notFound = true;

        while(results.next()){
            if("clients".equalsIgnoreCase(results.getString("TABLE_NAME"))){
                notFound = false;
                break;
            }
        }
        if(notFound){
            connection.createStatement()
                    .execute("CREATE TABLE clients (id varchar(50) primary key, fullName varchar(50), email varchar(30), password varchar(30), phoneNumber varchar(11), cardNumber varchar(16), address varchar(50))");
        }
    }
    public static ClientsDatabase getDatabaseInstance1() {
        if (instance == null)
            try {
                instance = new ClientsDatabase();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        return instance;
    }

    public Client createClient(String[] values) throws SQLException {
        Client client = new Client(values[0], values[1], values[2],values[3], values[4], values[5]);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO clients VALUES (?,?,?,?,?,?,?)");
        statement.setString(1,client.getID());
        for(int i = 2; i <= 7; i++ )
            statement.setString(i,values[i-2]);
        if(statement.executeUpdate() == 1){
            return client;
        }
        return null;
    }
    public ArrayList<Client> readClients() throws SQLException {
        ResultSet results = connection.createStatement().executeQuery("SELECT * FROM clients");
        ArrayList<Client> clients = new ArrayList<>();
        while(results.next()){
            clients.add(new Client(results.getString(1), results.getString(2),
                    results.getString(3), results.getString(4), results.getString(5),
                    results.getString(6),results.getString(7)));
        }
        return clients;
    }
    public boolean updateClient(String id, String[] values) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE clients SET fullName = ?, email = ?, password = ?, phoneNumber = ?, cardNumber = ?, address = ? WHERE id = ?");
        for (int i = 1; i <= 6; i++)
            statement.setString(i, values[i - 1]);
        statement.setString(7, id);
        return statement.executeUpdate() == 1;
    }

    public boolean deleteClient(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM clients WHERE id = ?");
        statement.setString(1, id);
        return statement.executeUpdate() == 1;
    }
}

