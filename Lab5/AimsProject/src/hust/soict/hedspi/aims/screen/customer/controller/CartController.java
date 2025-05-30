package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store; // Import Store

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Predicate;

public class CartController {

    private Cart cart;
    private Store store; // Thêm thuộc tính Store (Note section)
    private ObservableList<Media> cartItems;

    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, Integer> colMediaId;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;
    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private Label costLabel;
    @FXML private TextField tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;
    @FXML private ToggleGroup filterCategory;

    // Cập nhật constructor để nhận cả Cart và Store
    public CartController(Cart cart, Store store) { // (Note section)
        this.cart = cart;
        this.store = store; // Khởi tạo store
    }

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        if (cart.getItemsOrdered() instanceof ObservableList) {
            this.cartItems = (ObservableList<Media>) cart.getItemsOrdered();
        } else {
            this.cartItems = FXCollections.observableArrayList(cart.getItemsOrdered());
        }
        
        tblMedia.setItems(this.cartItems);
        updateTotalCost();

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateButtonBar(newValue)
        );

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            showFilteredMedia(newValue);
        });
        
        filterCategory.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            showFilteredMedia(tfFilter.getText());
        });
    }

    void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            btnPlay.setVisible(media instanceof Playable);
        }
    }
    
    private void updateTotalCost() {
        costLabel.setText(String.format("%.2f $", cart.totalCost()));
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null) {
            cart.removeMedia(selectedMedia);
            if (!(cart.getItemsOrdered() instanceof ObservableList)) {
                 this.cartItems.setAll(cart.getItemsOrdered());
            }
            updateTotalCost();
            tblMedia.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null && selectedMedia instanceof Playable) {
            ((Playable) selectedMedia).play();
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle("Playing Media");
            alert.setHeaderText(null);
            alert.setContentText("Playing: " + selectedMedia.getTitle());
            alert.showAndWait();
        }
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) { //
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/globalict/dsai/aims/screen/customer/view/Store.fxml"; //
            FXMLLoader loader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH)); //
            
            // Truyền cả store và cart cho ViewStoreController
            ViewStoreController storeController = new ViewStoreController(this.store, this.cart); // (cần truyền cả cart)
            loader.setController(storeController); //
            
            Parent root = loader.load(); //
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //
            stage.setScene(new Scene(root)); //
            stage.setTitle("Store"); //
            stage.show(); //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
            alert.setTitle("Empty Cart");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty. Please add items before placing an order.");
            alert.showAndWait();
            return;
        }
        cart.emptyCart();
        updateTotalCost();
        if (!(cart.getItemsOrdered() instanceof ObservableList)) {
            this.cartItems.clear();
        }
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been placed successfully! Your cart is now empty.");
        alert.showAndWait();
    }

    private void showFilteredMedia(String filterText) {
        Predicate<Media> predicate = media -> {
            if (filterText == null || filterText.isEmpty()) return true;
            String lowerCaseFilterText = filterText.toLowerCase();
            if (radioBtnFilterTitle.isSelected()) {
                return media.getTitle().toLowerCase().contains(lowerCaseFilterText);
            } else if (radioBtnFilterId.isSelected()) {
                return String.valueOf(media.getId()).contains(lowerCaseFilterText);
            }
            return true;
        };

        if (tblMedia.getItems() instanceof FilteredList) {
            ((FilteredList<Media>) tblMedia.getItems()).setPredicate(predicate);
        } else {
            FilteredList<Media> filteredData = new FilteredList<>(this.cartItems, p -> true);
            filteredData.setPredicate(predicate);
            tblMedia.setItems(filteredData);
        }
    }
}