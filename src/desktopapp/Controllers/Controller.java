package desktopapp.Controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import desktopapp.Service;
import desktopapp.Models.PC;
import desktopapp.Models.Phone;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TabPane tabs;

    @FXML
    private TextField txtMinPricePC;

    @FXML
    private TextField txtMaxPricePC;

    @FXML
    private ComboBox<String> cBoxBrandPC;

    @FXML
    private ComboBox<String> cBoxModelPC;

    @FXML
    private ComboBox<String> cBoxProcessorPC;

    @FXML
    private TextField txtMinMemoryPC;

    @FXML
    private TextField txtMaxMemoryPC;

    @FXML
    private TextField txtMinStoragePC;

    @FXML
    private TextField txtMinScreenPC;

    @FXML
    private TextField txtMaxScreenPC;

    @FXML
    private ComboBox<String> cBoxMinRatingPC;

    @FXML
    private CheckBox checkBoxLargeMemoryPC;

    @FXML
    private CheckBox checkBoxLargeStoragePC;

    @FXML
    private CheckBox checkBoxTouchscreenPC;

    @FXML
    private CheckBox checkBoxLongBatteryPC;

    @FXML
    private CheckBox checkBoxFaceRecPC;

    @FXML
    private ListView<PC> listViewPC;

    @FXML
    private Button btnApplyFilterPC;

    @FXML
    private TextField txtMinPricePhone;

    @FXML
    private TextField txtMaxPricePhone;

    @FXML
    private ComboBox<String> cBoxBrandPhone;

    @FXML
    private ComboBox<String> cBoxModelPhone;

    @FXML
    private TextField txtMinStoragePhone;

    @FXML
    private TextField txtMinScreenPhone;

    @FXML
    private TextField txtMaxScreenPhone;

    @FXML
    private ComboBox<String> cBoxMinRatingPhone;

    @FXML
    private CheckBox checkBoxLargeScreenPhone;

    @FXML
    private CheckBox checkBoxLargeStoragePhone;

    @FXML
    private CheckBox checkBoxTouchscreenPhone;

    @FXML
    private CheckBox checkBoxLongBatteryPhone;

    @FXML
    private CheckBox checkBoxFaceRecPhone;

    @FXML
    private ListView<Phone> listViewPhone;

    @FXML
    private Button btnApplyFilterPhone;

    private static Service<PC> pcService;
    private static Service<Phone> phoneService;

    List<PC> computers;
    List<Phone> phones;

    Hashtable<String, String> filters;

    public Controller() throws IOException, URISyntaxException {
        super();
        pcService = new Service<PC>(PC.class);
        phoneService = new Service<Phone>(Phone.class);

        filters = new Hashtable<String, String>();

        computers = pcService.getProducts();
        phones = phoneService.getProducts();
    }

    public void registerPCHandlers() {
        cBoxBrandPC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldProduct, String newProduct) {
                filters.put("brand", newProduct);
            }
        });

        cBoxModelPC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldProduct, String newProduct) {
                filters.put("model", newProduct);
            }
        });

        cBoxProcessorPC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldProduct, String newProduct) {
                filters.put("processor", newProduct);
            }
        });

        checkBoxLargeMemoryPC.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    filters.put("largeMemory", "true");
                } else {
                    filters.remove("largeMemory");
                }
            }
        });

        checkBoxLargeStoragePC.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    filters.put("largeStorage", "true");
                } else {
                    filters.remove("largeStorage");
                }
            }
        });

        btnApplyFilterPC.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    if (txtMinPricePC.getText() != null && !txtMinPricePC.getText().trim().isEmpty()) {
                        filters.put("minPrice", txtMinPricePC.getText());
                    }
                    if (txtMaxPricePC.getText() != null && !txtMaxPricePC.getText().trim().isEmpty()) {
                        filters.put("maxPrice", txtMaxPricePC.getText());
                    }

                    if (txtMinMemoryPC.getText() != null && !txtMinMemoryPC.getText().trim().isEmpty()) {
                        filters.put("minMemory", txtMinMemoryPC.getText());
                    }
                    if (txtMaxMemoryPC.getText() != null && !txtMaxMemoryPC.getText().trim().isEmpty()) {
                        filters.put("maxMemory", txtMaxMemoryPC.getText());
                    }

                    if (txtMinStoragePC.getText() != null && !txtMinStoragePC.getText().trim().isEmpty()) {
                        filters.put("minStorage", txtMinStoragePC.getText());
                    }

                    if (txtMinScreenPC.getText() != null && !txtMinScreenPC.getText().trim().isEmpty()) {
                        filters.put("minScreen", txtMinScreenPC.getText());

                    }
                    if (txtMaxScreenPC.getText() != null && !txtMaxScreenPC.getText().trim().isEmpty()) {
                        filters.put("maxScreen", txtMaxScreenPC.getText());
                    }

                    List<PC> filtered = pcService.getProductsByFilter(filters);

                    listViewPC.setItems(FXCollections.observableArrayList(filtered));
                    listViewPC.setCellFactory(param -> new ListCell<PC>() {
                        @Override
                        protected void updateItem(PC item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty || item == null || (item.getBrand() == null && item.getModel() == null)) {
                                setText(null);
                            } else {
                                String text = item.getBrand() + " " + item.getModel();
                                if (item.getLabel() != null) {
                                    text = text.concat(" with " + item.getLabel());
                                }
                                setText(text);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void registerPhoneHandlers() {
        cBoxBrandPhone.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldProduct, String newProduct) {
                filters.put("brand", newProduct);
            }
        });

        cBoxModelPhone.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldProduct, String newProduct) {
                filters.put("model", newProduct);
            }
        });

        checkBoxLargeScreenPhone.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    filters.put("largeScreen", "true");
                } else {
                    filters.remove("largeScreen");
                }
            }
        });

        checkBoxLargeStoragePhone.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    filters.put("largeStorage", "true");
                } else {
                    filters.remove("largeStorage");
                }
            }
        });

        btnApplyFilterPhone.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    if (txtMinPricePhone.getText() != null && !txtMinPricePhone.getText().trim().isEmpty()) {
                        filters.put("minPrice", txtMinPricePhone.getText());
                    }
                    if (txtMaxPricePhone.getText() != null && !txtMaxPricePhone.getText().trim().isEmpty()) {
                        filters.put("maxPrice", txtMaxPricePhone.getText());
                    }

                    if (txtMinStoragePhone.getText() != null && !txtMinStoragePhone.getText().trim().isEmpty()) {
                        filters.put("minStorage", txtMinStoragePhone.getText());
                    }

                    if (txtMinScreenPhone.getText() != null && !txtMinScreenPhone.getText().trim().isEmpty()) {
                        filters.put("minScreen", txtMinScreenPhone.getText());

                    }
                    if (txtMaxScreenPhone.getText() != null && !txtMaxScreenPhone.getText().trim().isEmpty()) {
                        filters.put("maxScreen", txtMaxScreenPhone.getText());
                    }

                    List<Phone> filtered = phoneService.getProductsByFilter(filters);

                    listViewPhone.setItems(FXCollections.observableArrayList(filtered));
                    listViewPhone.setCellFactory(param -> new ListCell<Phone>() {
                        @Override
                        protected void updateItem(Phone item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty || item == null || (item.getBrand() == null && item.getModel() == null)) {
                                setText(null);
                            } else {
                                String text = item.getBrand() + " " + item.getModel();
                                if (item.getLabel() != null) {
                                    text = text.concat(" with " + item.getLabel());
                                }
                                setText(text);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void registerEventHandlers() {
        tabs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                filters.clear();
            }
        });

        registerPCHandlers();
        registerPhoneHandlers();

    }

    public void setBrandFilterPC() {
        ArrayList<String> brands = new ArrayList<String>();
        computers.forEach(product -> {
            if (!brands.contains(product.getBrand())) {
                brands.add(product.getBrand());
            }
        });
        cBoxBrandPC.setItems(FXCollections.observableArrayList(brands));
    }

    public void setModelFilterPC() {
        ArrayList<String> models = new ArrayList<String>();
        computers.forEach(product -> {
            if (!models.contains(product.getModel())) {
                models.add(product.getModel());
            }
        });
        cBoxModelPC.setItems(FXCollections.observableArrayList(models));
    }

    public void setProcessorFilterPC() {
        ArrayList<String> processors = new ArrayList<String>();
        computers.forEach(product -> {
            if (!processors.contains(product.getProcessor())) {
                processors.add(product.getProcessor());
            }
        });
        cBoxProcessorPC.setItems(FXCollections.observableArrayList(processors));
    }

    public void setRatingFilterPC() {
        ArrayList<String> ratings = new ArrayList<String>() {
            {
                add("1.0");
                add("2.0");
                add("3.0");
                add("4.0");
                add("5.0");
            }
        };
        cBoxMinRatingPC.setItems(FXCollections.observableArrayList(ratings));
    }

    public void setBrandFilterPhone() {
        ArrayList<String> brands = new ArrayList<String>();
        phones.forEach(product -> {
            if (!brands.contains(product.getBrand())) {
                brands.add(product.getBrand());
            }
        });
        cBoxBrandPhone.setItems(FXCollections.observableArrayList(brands));
    }

    public void setModelFilterPhone() {
        ArrayList<String> models = new ArrayList<String>();
        phones.forEach(product -> {
            if (!models.contains(product.getModel())) {
                models.add(product.getModel());
            }
        });
        cBoxModelPhone.setItems(FXCollections.observableArrayList(models));
    }

    public void setRatingFilterPhone() {
        ArrayList<String> ratings = new ArrayList<String>() {
            {
                add("1.0");
                add("2.0");
                add("3.0");
                add("4.0");
                add("5.0");
            }
        };
        cBoxMinRatingPhone.setItems(FXCollections.observableArrayList(ratings));
    }

    public void setFilters() throws IOException {
        setBrandFilterPC();
        setModelFilterPC();
        setProcessorFilterPC();
        setRatingFilterPC();

        setBrandFilterPhone();
        setModelFilterPhone();
        setRatingFilterPhone();
    }

    @FXML
    public void initialize() throws IOException {
        registerEventHandlers();
        setFilters();
    }
}
