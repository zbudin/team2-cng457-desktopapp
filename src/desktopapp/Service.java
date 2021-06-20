package desktopapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;

import desktopapp.Controllers.Controller;
import desktopapp.Models.PC;
import desktopapp.Models.Phone;
import desktopapp.Models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Service<T> {
    ServicePaths paths = new ServicePaths();
    final ObjectMapper mapper = new ObjectMapper();
    private final Class<T> type;

    public Service(Class<T> type) {
        super();
        this.type = type;
    }

    private String encodeURL(String value) throws URISyntaxException, MalformedURLException {
        System.out.println(value);
        URL url = new URL(value);
        URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(),
                url.getQuery(), url.getRef());

        return uri.toASCIIString();
    }

    private String buildQuery(Hashtable<String, String> queryStrings) {
        final StringBuilder builder = new StringBuilder();
        queryStrings.forEach((k, v) -> {
            if (k.contains("feature")) {
                builder.append("feature=" + v.toString() + "&");
            } else {
                builder.append(k.toString() + "=" + v.toString() + "&");
            }
        });
        String query = "?" + builder.toString();

        return query.substring(0, query.length() - 1);
    }

    private HttpURLConnection createConnection(String path) throws URISyntaxException, IOException {
        URL url = new URL(encodeURL(paths.getURL() + paths.get(path)));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");

        return connection;
    }

    private HttpURLConnection createConnection(String path, String queryString) throws URISyntaxException, IOException {
        URL url = new URL(encodeURL(paths.getURL() + paths.get(path) + queryString));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");

        return connection;
    }

    public InputStream get(String path) throws IOException, URISyntaxException {
        HttpURLConnection connection = createConnection(path);
        InputStream responseStream = connection.getInputStream();

        return responseStream;
    }

    public InputStream get(String path, Hashtable<String, String> queryStrings) throws IOException, URISyntaxException {
        String queryString = buildQuery(queryStrings);
        HttpURLConnection connection = createConnection(path, queryString);
        InputStream responseStream = connection.getInputStream();

        return responseStream;
    }

    public List<T> setLabels(List<T> products) {
        if (this.type == PC.class) {
            products.forEach(item -> {
                Boolean set = false;
                String label = null;
                if (((PC) item).getMemory() > 16) {
                    label = "large memory";
                    set = true;
                }

                if (((PC) item).getStorage() > 16) {
                    if (set) {
                        label += " and large storage";
                    } else {
                        label = "large storage";
                    }
                }
                ((Product) item).setLabel(label);
            });
        } else if (this.type == Phone.class) {
            products.forEach(item -> {
                Boolean set = false;
                String label = null;
                if (Double.parseDouble(((Phone) item).getScreenSize()) > 6.0) {
                    label = "large screen";
                    set = true;
                }

                if (((Phone) item).getInternalMemory() > 128) {
                    if (set) {
                        label += " and large storage";
                    } else {
                        label = "large storage";
                    }
                }
                ((Product) item).setLabel(label);
            });
        }

        return products;
    }

    public List<T> filter(List<T> products, Hashtable<String, String> filters) {
        if (this.type == PC.class) {
            if (filters.get("minMemory") != null) {
                products.removeIf(product -> ((PC) product).getMemory() < Integer.parseInt(filters.get("minMemory")));
            }
            if (filters.get("maxMemory") != null) {
                products.removeIf(product -> ((PC) product).getMemory() > Integer.parseInt(filters.get("maxMemory")));
            }
            if (filters.get("minStorage") != null) {
                products.removeIf(product -> ((PC) product).getStorage() < Integer.parseInt(filters.get("minStorage")));
            }
            if (filters.get("maxStorage") != null) {
                products.removeIf(product -> ((PC) product).getStorage() > Integer.parseInt(filters.get("maxStorage")));
            }
            if (filters.get("minScreen") != null) {
                System.out.println("minScreen" + filters.get("minScreen"));
                products.removeIf(product -> (Double.parseDouble(((PC) product).getScreenSize()) < Double
                        .parseDouble(filters.get("minScreen"))));
            }
            if (filters.get("maxScreen") != null) {
                products.removeIf(product -> (Double.parseDouble(((PC) product).getScreenSize()) < Double
                        .parseDouble(filters.get("maxScreen"))));
            }

        } else if (this.type == Phone.class) {
            if (filters.get("minScreen") != null) {
                products.removeIf(product -> (Double.parseDouble(((Phone) product).getScreenSize()) < Double
                        .parseDouble(filters.get("minScreen"))));
            }
            if (filters.get("maxScreen") != null) {
                products.removeIf(product -> (Double.parseDouble(((Phone) product).getScreenSize()) < Double
                        .parseDouble(filters.get("maxScreen"))));
            }
        }

        return products;
    }

    public List<T> filterLabels(List<T> products, Hashtable<String, String> filters) {
        if (this.type == PC.class) {
            if (filters.get("largeMemory") != null) {
                products.removeIf(product -> !(((PC) product).getLabel().contains("large memory")));
            }
            if (filters.get("largeStorage") != null) {
                products.removeIf(product -> !(((PC) product).getLabel().contains("large storage")));
            }
        } else if (this.type == Phone.class) {
            if (filters.get("largeScreen") != null) {
                products.removeIf(product -> !(((Phone) product).getLabel().contains("large screen")));
            }
            if (filters.get("largeStorage") != null) {
                products.removeIf(product -> !(((Phone) product).getLabel().contains("large storage")));
            }
        }
        return products;
    }

    public List<T> getProducts() throws IOException, URISyntaxException {
        InputStream responseStream;
        if (this.type == PC.class) {
            responseStream = get("COMPUTERS");
        } else {
            responseStream = get("PHONES");
        }
        List<T> response = mapper.readValue(responseStream,
                mapper.getTypeFactory().constructParametricType(List.class, this.type));
        response = setLabels(response);
        return response;
    }

    public List<T> getProductsByFilter(Hashtable<String, String> filters) throws IOException, URISyntaxException {
        InputStream responseStream;
        if (this.type == PC.class) {
            responseStream = get("COMPUTERS_SEARCH",filters);
        } else {
            responseStream = get("PHONES_SEARCH",filters);
        }
        List<T> response = mapper.readValue(responseStream,
                mapper.getTypeFactory().constructParametricType(List.class, this.type));

        response = filter(response, filters);
        response = setLabels(response);

        return response;
    }

}
