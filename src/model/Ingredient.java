package model;

import java.util.Date;
import java.util.UUID;

public class Ingredient {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private Date expirydate;

    public Ingredient(String name, Date expirydate) {
        this.name = name;
        this.expirydate = expirydate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", expirydate=" + expirydate +
                '}';
    }
}
