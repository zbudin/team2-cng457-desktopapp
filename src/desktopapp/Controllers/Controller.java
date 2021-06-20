package desktopapp.Controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import desktopapp.Models.Comment;
import desktopapp.Models.Product;
import desktopapp.Service;
import desktopapp.Models.PC;
import desktopapp.Models.Phone;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.json.JSONArray;

public class Controller{

    private static boolean isEmpty[] = {true,true,true,true,true,true,true,true};

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
    private RadioButton radioTouchscreenPC;

    @FXML
    private RadioButton radioLongBatteryPC;

    @FXML
    private RadioButton radioFaceRecPC;

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
    private RadioButton radioTouchscreenPhone;

    @FXML
    private RadioButton radioLongBatteryPhone;

    @FXML
    private RadioButton radioFaceRecPhone;

    @FXML
    private ListView<Phone> listViewPhone;


    @FXML private ScrollPane pcInfoField;
    @FXML private ScrollPane pcCmp1;
    @FXML private ScrollPane pcCmp2;
    @FXML private ScrollPane pcCmp3;
    @FXML private Button pcRM1Btn;
    @FXML private Button pcRM2Btn;
    @FXML private Button pcRM3Btn;

    @FXML private ScrollPane phoneInfoField;
    @FXML private ScrollPane phoneCmp1;
    @FXML private ScrollPane phoneCmp2;
    @FXML private ScrollPane phoneCmp3;
    @FXML private Button phoneRM1Btn;
    @FXML private Button phoneRM2Btn;
    @FXML private Button phoneRM3Btn;

    @FXML private Button pcCmpBtn;
    @FXML private Button phoneCmpBtn;
    @FXML private Button pcSortByPriceBtn;
    @FXML private Button pcSortByRatingBtn;
    @FXML private Button phoneSortByPriceBtn;
    @FXML private Button phoneSortByRatingBtn;


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
                if (newProduct != null) {
                    filters.put("brand", newProduct);
                }
            }
        });

        cBoxModelPC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldProduct, String newProduct) {
                if (newProduct != null) {
                    filters.put("model", newProduct);
                }
            }
        });

        cBoxProcessorPC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldProduct, String newProduct) {
                if (newProduct != null) {
                    filters.put("processor", newProduct);
                }
            }
        });

        checkBoxLargeMemoryPC.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (newValue != null) {
                    filters.put("largeMemory", "true");
                } else {
                    filters.remove("largeMemory");
                }
            }
        });

        radioFaceRecPC.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    filters.put("feature1", "Face Recognition");
                } else {
                    filters.remove("feature1");
                }
            }
        });
        radioTouchscreenPC.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    filters.put("feature2", "Touch Screen");
                } else {
                    filters.remove("feature2");
                }
            }
        });
        radioLongBatteryPC.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    filters.put("feature3", "Extra-Long Battery Life");
                } else {
                    filters.remove("feature3");
                }
            }
        });

        checkBoxLargeStoragePC.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue != null) {
                    filters.put("largeStorage", "true");
                } else {
                    filters.remove("largeStorage");
                }
            }
        });

        cBoxMinRatingPC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldProduct, String newProduct) {
                if(newProduct != null){filters.put("avgRating", newProduct);}
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
                    for(PC p: filtered){
                        p.setAvgRating(calculateAvg(getComments(p.getId(),false)));
                    }
                    if (filters.get("avgRating") != null) {
                        filtered.removeIf(product -> ((((PC) product).getAvgRating()) < Float
                                .parseFloat(filters.get("avgRating"))));
                    }

                    listViewPC.setItems(FXCollections.observableArrayList(filtered));
                    listViewPC.setCellFactory(param -> new ListCell<PC>() {
                        @Override
                        protected void updateItem(PC item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty || item == null || (item.getBrand() == null && item.getModel() == null)) {
                                setText(null);
                            } else {
                                String text = item.getBrand() + " " + item.getModel();
                                if (item.getLabel() != null && !item.getLabel().isEmpty()) {
                                    text = text.concat(" with " + item.getLabel());
                                }
                                setText(text);
                            }
                        }
                    });

                    txtMinPricePC.setText(null);
                    txtMaxPricePC.setText(null);
                    txtMinMemoryPC.setText(null);
                    txtMinScreenPC.setText(null);
                    txtMaxScreenPC.setText(null);

                    cBoxBrandPC.getSelectionModel().clearSelection();
                    cBoxModelPC.getSelectionModel().clearSelection();
                    cBoxProcessorPC.getSelectionModel().clearSelection();

                    radioFaceRecPC.setSelected(false);
                    checkBoxLargeMemoryPC.setSelected(false);
                    checkBoxLargeStoragePC.setSelected(false);
                    radioLongBatteryPC.setSelected(false);
                    radioTouchscreenPC.setSelected(false);

                    filters.clear();
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

                if(newProduct != null){filters.put("brand", newProduct);}
            }
        });

        cBoxMinRatingPhone.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldProduct, String newProduct) {
                if(newProduct != null){filters.put("avgRating", newProduct);}
            }
        });

        cBoxModelPhone.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldProduct, String newProduct) {
                if (newProduct != null) {
                    filters.put("model", newProduct);
                }
            }
        });

        radioFaceRecPhone.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    filters.put("feature1", "Face Recognition");
                } else {
                    filters.remove("feature1");
                }
            }
        });
        radioTouchscreenPhone.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    filters.put("feature2", "Touch Screen");
                } else {
                    filters.remove("feature2");
                }
            }
        });
        radioLongBatteryPhone.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    filters.put("feature3", "Extra-Long Battery Life");
                } else {
                    filters.remove("feature3");
                }
            }
        });

        checkBoxLargeScreenPhone.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue != null) {
                    filters.put("largeScreen", "true");
                } else {
                    filters.remove("largeScreen");
                }
            }
        });

        checkBoxLargeStoragePhone.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue != null) {
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
                    for(Phone p: filtered){
                        p.setAvgRating(calculateAvg(getComments(p.getId(),false)));
                    }
                    if (filters.get("avgRating") != null) {
                        filtered.removeIf(product -> ((((Phone) product).getAvgRating()) < Float
                                .parseFloat(filters.get("avgRating"))));
                    }
                    listViewPhone.setItems(FXCollections.observableArrayList(filtered));
                    listViewPhone.setCellFactory(param -> new ListCell<Phone>() {
                        @Override
                        protected void updateItem(Phone item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty || item == null || (item.getBrand() == null && item.getModel() == null)) {
                                setText(null);
                            } else {
                                String text = item.getBrand() + " " + item.getModel();
                                if (item.getLabel() != null && !item.getLabel().isEmpty()) {
                                    text = text.concat(" with " + item.getLabel());
                                }
                                setText(text);
                            }
                        }
                    });


                    txtMinPricePhone.setText(null);
                    txtMaxPricePhone.setText(null);
                    txtMinStoragePhone.setText(null);
                    txtMinScreenPhone.setText(null);
                    txtMaxScreenPhone.setText(null);

                    cBoxBrandPhone.getSelectionModel().clearSelection();
                    cBoxModelPhone.getSelectionModel().clearSelection();

                    radioFaceRecPhone.setSelected(false);
                    checkBoxLargeStoragePhone.setSelected(false);
                    radioLongBatteryPhone.setSelected(false);
                    radioTouchscreenPhone.setSelected(false);

                    filters.clear();
                  
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
                add("0.0");
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
                add("0.0");
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
        freeCmp(0,true);
        freeCmp(1,true);
        freeCmp(2,true);
        freeCmp(3,true);
        freeCmp(0,false);
        freeCmp(1,false);
        freeCmp(2,false);
        freeCmp(3,false);
        showAllPc();
        showAllPhone();
        selectionEventHandlers();
        registerEventHandlers();
        setFilters();
    }

    public void selectionEventHandlers() throws IOException{
        listViewPC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                Object o = listViewPC.getSelectionModel().getSelectedItem();
                if(o!=null) {
                    PC pc = (PC) o;
                    showInfo(pc, true,0);
                }
            }
        });

        listViewPC.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                Object o = listViewPC.getSelectionModel().getSelectedItem();
                if(o!=null) {
                    PC c = (PC) o;
                    showInfo(c, true,0);
                }
            }
        });

        listViewPhone.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object o = listViewPhone.getSelectionModel().getSelectedItem();
                if(o!=null) {
                    Phone c = (Phone) o;
                    showInfo(c, false,0);
                }
            }
        });

        listViewPhone.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                Object o = listViewPhone.getSelectionModel().getSelectedItem();
                if(o!=null) {
                    Phone c = (Phone) o;
                    showInfo(c, false,0);
                }
            }
        });
    }

    public void sortPressed(ActionEvent event){
        String test = event.toString();
        try {
            if (test.contains("pc")) {
                ObservableList ol = listViewPC.getItems();
                if (test.contains("Price")) {
                    Comparator<PC> priceComparator = Comparator.comparing(PC::getPrice);
                    ol.sort(priceComparator.reversed());
                } else {
                    for (int i = 0; i < ol.size(); i++) {
                        PC temp = (PC) ol.get(i);
                        temp.setAvgRating(calculateAvg(getComments(temp.getId(), true)));
                        ol.set(i,temp);
                    }
                    Comparator<PC> ratingComparator = Comparator.comparing(PC::getAvgRating);
                    ol.sort(ratingComparator.reversed());
                }
            } else {
                ObservableList ol = listViewPhone.getItems();
                if (test.contains("Price")) {
                    Comparator<Phone> commentComparator = Comparator.comparing(Phone::getPrice);
                    ol.sort(commentComparator.reversed());
                } else {
                    for (int i = 0; i < ol.size(); i++) {
                        Phone temp = (Phone) ol.get(i);
                        temp.setAvgRating(calculateAvg(getComments(temp.getId(), true)));
                        ol.set(i,temp);
                    }
                    Comparator<Phone> ratingComparator = Comparator.comparing(Phone::getAvgRating);
                    ol.sort(ratingComparator.reversed());
                }
            }
        }
        catch(Exception e){System.out.println(e);}
    }


    public void comparePressed(ActionEvent event){
        String test = event.toString();
        if(test.contains("pc")){
            if(isEmpty[0]){
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Please select an item!");
                a.setHeaderText("Please select an item first!");
                a.setContentText("You should select an item first and then press compare button to compare multiple computers.");
                a.show();
            }
            else if(isEmpty[1]){
                PC o = (PC)listViewPC.getSelectionModel().getSelectedItem();
                showInfo(o,true,1);

            }
            else if(isEmpty[2]){
                PC o = (PC)listViewPC.getSelectionModel().getSelectedItem();
                showInfo(o,true,2);
            }
            else if(isEmpty[3]){
                PC o = (PC)listViewPC.getSelectionModel().getSelectedItem();
                showInfo(o,true,3);
            }
            else{
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Please remove an item!");
                a.setHeaderText("All comparison fields are full!");
                a.setContentText("You should clear a comparison field by pressing one of the remove buttons located on the left.");
                a.show();
            }
        }
        else{
            if(isEmpty[4]){
                System.out.println(isEmpty[4]);
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Please select an item!");
                a.setHeaderText("Please select an item first!");
                a.setContentText("You should select an item first and then press compare button to compare multiple computers.");
                a.show();
            }
            else if(isEmpty[5]){
                Phone o = (Phone)listViewPhone.getSelectionModel().getSelectedItem();
                showInfo(o,false,1);
            }
            else if(isEmpty[6]){
                Phone o = (Phone)listViewPhone.getSelectionModel().getSelectedItem();
                showInfo(o,false,2);
            }
            else if(isEmpty[7]){
                Phone o = (Phone)listViewPhone.getSelectionModel().getSelectedItem();
                showInfo(o,false,3);
            }
            else{
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Please remove an item!");
                a.setHeaderText("All comparison fields are full!");
                a.setContentText("You should clear a comparison field by pressing one of the remove buttons located on the left.");
                a.show();
            }
        }
    }

    private void freeCmp(int id,boolean isPc){
        Text text = new Text("");
        ScrollPane ref;
        if(isPc){
            switch(id){
                case 0: ref = pcInfoField; break;
                case 1: ref = pcCmp1; break;
                case 2: ref = pcCmp2; break;
                default: ref = pcCmp3; break;
            }
        }
        else{
            switch(id){
                case 0: ref = phoneInfoField; break;
                case 1: ref = phoneCmp1; break;
                case 2: ref = phoneCmp2; break;
                default: ref = phoneCmp3; break;
            }
            id += 4;
        }
        isEmpty[id] = true;
        ref.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        ref.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        ref.setContent(text);
    }

    private void showInfo(Product p,boolean isPc,int id){
        ScrollPane ref;
        long productID;
        if(isPc){
            productID = ((PC) listViewPC.getSelectionModel().getSelectedItem()).getId();
            switch(id){
                case 0: ref = pcInfoField; break;
                case 1: ref = pcCmp1; break;
                case 2: ref = pcCmp2; break;
                default: ref = pcCmp3; break;
            }
        }
        else{
            productID = ((Phone) listViewPhone.getSelectionModel().getSelectedItem()).getId();
            switch(id){
                case 0: ref = phoneInfoField; break;
                case 1: ref = phoneCmp1; break;
                case 2: ref = phoneCmp2; break;
                default: ref = phoneCmp3; break;
            }
            id += 4;
        }
        ref.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        ref.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        String temp = p.returnDetails();
        int count = 0;
        try {
            String strings[] = getFeatures(productID,isPc);
            Comment comments[] = getComments(productID, isPc);
            for (String s:strings){
                count++;
                temp += "\nAdditional Feature " + count + ": " + s;
            }
            if(comments.length>0) {
                temp += "\n\nComments\n";
                if(id>=4){
                    Phone phone = (Phone) listViewPhone.getSelectionModel().getSelectedItem();
                    phone.setAvgRating(calculateAvg(comments));
                    temp += "\nAverage Rating:" + String.format("%.2f", phone.getAvgRating());
                }
                else{
                    PC pc = (PC) listViewPC.getSelectionModel().getSelectedItem();
                    pc.setAvgRating(calculateAvg(comments));
                    temp += "\nAverage Rating:" + String.format("%.2f", pc.getAvgRating());
                }
                if (id == 0 || id == 4) {
                    count = 0;
                    for (Comment c : comments) {
                        count++;
                        temp += "\nComment " + count + "\n" + c;
                    }
                } else {
                    count = 0;
                    for (Comment c : comments) {
                        count++;
                        temp += "\nComment " + count + "\n" + c;
                        if (count == 3) {
                            break;
                        }
                    }
                }
            }
            else{
                temp += "\n\nNo one commented to this product yet!";
            }

        }
        catch(Exception e){
            System.out.println(e);
        }


        Text text = new Text(temp);
        ref.setContent(text);
        isEmpty[id] = false;



    }

    public void removePressed(ActionEvent event){
        String test = event.toString();
        if(test.contains("pc")){
            //Handle pc here
            if(test.contains("RM1")){
                if(isEmpty[1]){
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Already empty");
                    a.setHeaderText("Item 1 is already empty");
                    a.show();
                }
                else{
                    freeCmp(1,true);
                }
            }
            else if(test.contains("RM2")){
                if(isEmpty[2]){
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Already empty");
                    a.setHeaderText("Item 2 is already empty");
                    a.show();
                }
                else{
                    freeCmp(2,true);
                }
            }
            else{
                if(isEmpty[3]){
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Already empty");
                    a.setHeaderText("Item 3 is already empty");
                    a.show();
                }
                else{
                    freeCmp(3,true);
                }
            }
        }
        else{
            //Handle phone here
            if(test.contains("RM1")){
                if(isEmpty[5]){
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Already empty");
                    a.setHeaderText("Item 1 is already empty");
                    a.show();
                }
                else{
                    freeCmp(1,false);
                }
            }
            else if(test.contains("RM2")){
                if(isEmpty[6]){
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Already empty");
                    a.setHeaderText("Item 2 is already empty");
                    a.show();
                }
                else{
                    freeCmp(2,false);
                }
            }
            else{
                if(isEmpty[7]){
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Already empty");
                    a.setHeaderText("Item 3 is already empty");
                    a.show();
                }
                else{
                    freeCmp(3,false);
                }
            }
        }
    }

    private Comment[] getComments(long id,boolean isPc) throws IOException {
        HttpURLConnection connection;
        String url = "http://localhost:8080/";
        if(isPc){
            url += "computers/"+id+"/comments/";
            connection = (HttpURLConnection)new URL(url).openConnection();
        }
        else{
            url += "phones/"+id+"/comments/";
            connection = (HttpURLConnection)new URL(url).openConnection();
        }
        connection.setRequestMethod("GET");
        String response = "";
        int responseCode = connection.getResponseCode();
        if (responseCode==200){
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();
        }
        JSONArray jsonArray = new JSONArray(response);
        int length = jsonArray.length();
        Comment comments[] = new Comment[length];
        for(int i=0;i<length;i++){
            String s = jsonArray.getJSONObject(i).getString("comment");
            int r = jsonArray.getJSONObject(i).getInt("rating");
            long t = jsonArray.getJSONObject(i).getLong("timestamp");
            comments[i] = new Comment(s,r,t);
        }
        Comparator<Comment> commentComparator = Comparator.comparing(Comment::getTimestamp).reversed();
        Arrays.sort(comments,commentComparator);
        return comments;
    }

    private String[] getFeatures(long id,boolean isPc) throws IOException{
        HttpURLConnection connection;
        String url = "http://localhost:8080/";
        if(isPc){
            url += "computers/"+id+"/features/";
            connection = (HttpURLConnection)new URL(url).openConnection();
        }
        else{
            url += "phones/"+id+"/features/";
            connection = (HttpURLConnection)new URL(url).openConnection();
        }
        connection.setRequestMethod("GET");
        String response = "";
        int responseCode = connection.getResponseCode();
        if (responseCode==200){
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();
        }
        JSONArray jsonArray = new JSONArray(response);
        int length = jsonArray.length();
        String features[] = new String[length];
        for(int i=0;i<length;i++){
            features[i] = jsonArray.getJSONObject(i).getString("feature");
        }

        return features;
    }

    private float calculateAvg(Comment comments[]){
        float avg = 0;
        for(Comment c:comments){
            avg += c.getRating()/((float)(comments.length));
        }
        return avg;
    }

    private void showAllPc() throws IOException{
        String response = "";
        HttpURLConnection connection = (HttpURLConnection)new URL("http://localhost:8080/computers").openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode==200){
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();
        }
        JSONArray jsonArray = new JSONArray(response);
        int length = jsonArray.length();
        PC pc;
        for(int i=0;i<jsonArray.length();i++){
            pc = new PC();
            double r = jsonArray.getJSONObject(i).getDouble("price");
            pc.setPrice(r);
            String s = jsonArray.getJSONObject(i).getString("screenSize");
            pc.setScreenSize(s);
            String b = jsonArray.getJSONObject(i).getString("brand");
            pc.setBrand(b);
            String m = jsonArray.getJSONObject(i).getString("model");
            pc.setModel(m);
            String p = jsonArray.getJSONObject(i).getString("processor");
            pc.setProcessor(p);
            int mm = jsonArray.getJSONObject(i).getInt("memory");
            pc.setMemory(mm);
            int st = jsonArray.getJSONObject(i).getInt("storage");
            pc.setStorage(st);
            String res = jsonArray.getJSONObject(i).getString("screenResolution");
            pc.setScreenResolution(res);
            long id = jsonArray.getJSONObject(i).getLong("id");
            pc.setId(id);
            pc.setAvgRating(calculateAvg(getComments(id,true)));

            listViewPC.getItems().add(pc);
        }
    }

    private void showAllPhone() throws IOException{
        String response = "";
        HttpURLConnection connection = (HttpURLConnection)new URL("http://localhost:8080/phones").openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode==200){
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();
        }
        JSONArray jsonArray = new JSONArray(response);
        int length = jsonArray.length();
        Phone phone;
        for(int i=0;i<length;i++){

            phone = new Phone();
            double r = jsonArray.getJSONObject(i).getDouble("price");
            phone.setPrice(r);
            String s = jsonArray.getJSONObject(i).getString("screenSize");
            phone.setScreenSize(s);
            String b = jsonArray.getJSONObject(i).getString("brand");
            phone.setBrand(b);
            String m = jsonArray.getJSONObject(i).getString("model");
            phone.setModel(m);
            int im = jsonArray.getJSONObject(i).getInt("internalMemory");
            phone.setInternalMemory(im);
            long id = jsonArray.getJSONObject(i).getLong("id");
            phone.setId(id);
            phone.setAvgRating(calculateAvg(getComments(id,false)));
            listViewPhone.getItems().add(phone);
        }

    }
}
