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
}
