package model.accounts;


import java.util.UUID;

public class User {
    protected String ID ;
    protected String fullname;
    protected String email;
    protected String password;
    protected String phonenumber;
    protected String cardnumber;

    public User(String id, String fullname, String email, String password, String phonenumber,String cardnumber) {
        this.ID = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.cardnumber = cardnumber;
    }

    public User(String fullname, String email, String password, String phonenumber,String cardnumber) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.cardnumber = cardnumber;
        this.ID = UUID.randomUUID().toString();
    }

    public User() {
        this.ID = UUID.randomUUID().toString();
    }

    public String getFullname() {
        return fullname;
    }

    public String getID() {
        return ID;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
