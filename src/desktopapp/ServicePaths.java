package desktopapp;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

public class ServicePaths {
    private String domain = "http://localhost:8080/";
    private Map<String, String> dict;

    public ServicePaths() {
        Hashtable<String, String> map = new Hashtable<String, String>();
        map.put("COMPUTERS", "computers");
        map.put("COMPUTERS_SEARCH", "computers/search");
        map.put("PHONES", "phones");
        map.put("PHONES_SEARCH", "phones/search");

        dict = Collections.unmodifiableMap(map);
    }

    public String getURL() {
        return domain;
    }

    public String get(String key) {
        return dict.get(key);
    }
}
