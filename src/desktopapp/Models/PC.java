package desktopapp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PC extends Product {

    private String processor;

    private int memory;

    private String screenResolution;

    private int storage;

    public String getProcessor() {
        return processor;
    }

    public int getMemory() {
        return memory;
    }

    public int getStorage() {
        return storage;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setScreenResolution(String screenResolution){
        this.screenResolution = screenResolution;
    }
    public void setStorage(int storage){
        this.storage = storage;
    }
    public void setMemory(int memory) {
        this.memory = memory;
    }

    @Override
    public String toString(){
        String temp = "";
        if(memory>=16){
            temp += "large memory";
        }
        if(storage>=2000){
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
                "\nMemory: " + this.getMemory() +
                "\nStorage: " + this.getStorage() +
                "\nProcessor: " + this.processor +
                "\nScreen Resolution: " + this.screenResolution;
        if(!this.getLabel().isEmpty()){
            temp += "\nLabel: " + this.getLabel();
        }
        return temp;
    }
}
