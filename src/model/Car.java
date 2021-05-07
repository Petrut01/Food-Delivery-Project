package model;


public class Car {
    private String brand;
    private String model;
    private String numberplate;

    public Car() {
    }

    public Car(String brand, String model, String numberplate) {
        this.brand = brand;
        this.model = model;
        this.numberplate = numberplate;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumberplate() {
        return numberplate;
    }

    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", numberplate='" + numberplate + '\'' +
                '}';
    }

}
