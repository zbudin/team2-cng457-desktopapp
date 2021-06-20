package desktopapp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Long id;

    private String brand;

    private String model;

    private String screenSize;

    private Double price;

    private String label;

    private float avgRating;

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getLabel() {
        return label;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public Double getPrice() { return price; }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String returnDetails(){
        return "";
    }

}
