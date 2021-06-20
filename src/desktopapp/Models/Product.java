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

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
