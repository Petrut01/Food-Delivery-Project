package model;

import java.util.ArrayList;
import java.util.UUID;

public class Product {
    private String name;
    private double price;
    ArrayList<Ingredient> ingredients;
    private String restaurantId;

    public Product(String restaurantId, String name, double price, ArrayList<Ingredient> ingredients) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }
    public Product(String restaurantId, String name, double price) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Product(String name, double price, ArrayList<Ingredient> ingredients) {
        this.restaurantId = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public Product() {
        this.restaurantId = UUID.randomUUID().toString();
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Product(String[] values){
        this.restaurantId = values[0];
        this.name = values[1];
        this.price = Double.parseDouble(values[2]);
        if(values.length > 3)
        {
            this.ingredients = new ArrayList<Ingredient>();
            for (int i = 3 ; i < values.length; i++)
                ingredients.add(new Ingredient(values[i]));
        }

    }

    public Product(String name, double price) {
        this.restaurantId = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                '}';
    }

}
