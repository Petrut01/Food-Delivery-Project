package model.accounts;

public class Admin extends User{

    private Client client_admin;

    public Admin() {
    }

    public Admin(String fullname, String email, String password, String phonenumber, String cardnumber) {
        super(fullname, email, password, phonenumber, cardnumber);
        this.client_admin = new Client(fullname, email, password, phonenumber, cardnumber);
    }

    public Client getClient_admin() {
        return client_admin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
