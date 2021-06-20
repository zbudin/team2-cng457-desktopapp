package desktopapp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Phone extends Product {

    private int internalMemory;

    public int getInternalMemory() {
        return internalMemory;
    }

    public void setInternalMemory(int internalMemory) {
        this.internalMemory = internalMemory;
    }

    @Override
    public String toString(){
        String temp = "";
        if(Float.parseFloat(this.getScreenSize())>=6.0){
            temp += "large screen";
        }
        if(internalMemory>=128){
            if(temp.isEmpty()){
                temp += "large storage";
            }
            else{
                temp += " and large storage";
            }
        }

        this.setLabel(temp);
        if(!temp.isEmpty()){
            temp = this.getBrand() + " " + this.getModel() + " with " +temp;
        }
        else{
            temp = this.getBrand() + " " + this.getModel();
        }
        return temp;
    }


    @Override
    public String returnDetails(){
        String temp="Brand: " + this.getBrand() +
                "\nModel: " + this.getModel() +
                "\nScreenSize: " + this.getScreenSize() +
                "\nPrice: " + this.getPrice() +
                "\nInternal Memory: " + this.getInternalMemory();
        if(!this.getLabel().isEmpty()){
                temp += "\nLabel: " + this.getLabel();
        }
        return temp;
    }
}
