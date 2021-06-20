package desktopapp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Phone extends Product {

    private int internalMemory;

    public int getInternalMemory() {
        return internalMemory;
    }
}
