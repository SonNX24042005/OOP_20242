package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Predicate;

public class CartController { 

    private Cart cart;
    private Store store;

    @FXML
    private Button btnPlay; [cite: 180]

    @FXML
    private Button btnRemove; [cite: 180]

    @FXML
    private TableColumn<Media, String> colMediaCategory; [cite: 170]

    @FXML
    private TableColumn<Media, Float> colMediaCost; [cite: 170]

    @FXML
    private TableColumn<Media, Integer> colMediaId; [cite: 170]

    @FXML
    private TableColumn<Media, String> colMediaTitle; [cite: 170]

    @FXML
    private Label costLabel; [cite: 162]

    @FXML
    private TableView<Media> tblMedia; [cite: 153]

    @FXML
    private ToggleGroup filterCategory; [cite: 159]

    @FXML
    private TextField tfFilter; [cite: 192]

    @FXML
    private RadioButton radioBtnFilterId; [cite: 192]

    @FXML
    private RadioButton radioBtnFilterTitle; [cite: 192]

    public CartController(Store store, Cart cart) { [cite: 165, 199]
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id")); [cite: 170]
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title")); [cite: 171]
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category")); [cite: 173]
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost")); [cite: 175]

        if (cart.getItemsOrdered() != null) {
            tblMedia.setItems(cart.getItemsOrdered()); [cite: 171]
        }

        btnPlay.setVisible(false); [cite: 181]
        btnRemove.setVisible(false); [cite: 181]

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> updateButtonBar(newValue) [cite: 182]
        );

        updateTotalCost();
        cart.getItemsOrdered().addListener((javafx.collections.ListChangeListener.Change<? extends Media> c) -> {
            updateTotalCost();
        });

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> showFilteredMedia(newValue));
        filterCategory.selectedToggleProperty().addListener((observable, oldValue, newValue) -> showFilteredMedia(tfFilter.getText()));
    }

    void updateButtonBar(Media media) { [cite: 183]
        if (media == null) { [cite: 183]
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true); [cite: 183]
            if (media instanceof Playable) { [cite: 183]
                btnPlay.setVisible(true); [cite: 183]
            } else {
                btnPlay.setVisible(false); [cite: 183]
            }
        }
    }

    void updateTotalCost() {
        costLabel.setText(String.format("%.2f $", cart.totalCost()));
    }


    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null && media instanceof Playable) {
            try {
                ((Playable) media).play();
                showAlert(Alert.AlertType.INFORMATION, "Playing",
                        "Playing: " + media.getTitle());
            } catch (PlayerException e) {
                 showAlert(Alert.AlertType.ERROR, "Playback Error", e.getMessage());
            }
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) { [cite: 187]
        Media media = tblMedia.getSelectionModel().getSelectedItem(); [cite: 188]
        if (media != null) {
            cart.removeMedia(media); [cite: 188]
        } else {
             showAlert(Alert.AlertType.WARNING, "Selection Error", "Please select a media to remove.");
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Empty Cart", "Your cart is empty. Please add items before placing an order.");
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Order Placed",
                "Your order has been placed successfully!\nTotal cost: " +
                String.format("%.2f $", cart.totalCost()) +
                "\nThank you for shopping!");
        cart.emptyCart();
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) { [cite: 204]
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/dsai/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));

            fxmlLoader.setController(new ViewStoreController(store, cart));  

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFilteredMedia(String filterText) { [cite: 194]
        FilteredList<Media> filteredData = new FilteredList<>(cart.getItemsOrdered(), p -> true); [cite: 195]

        filteredData.setPredicate(media -> { [cite: 195]
            if (filterText == null || filterText.isEmpty()) {
                return true; 
            }

            String lowerCaseFilter = filterText.toLowerCase();
            RadioButton selectedRadio = (RadioButton) filterCategory.getSelectedToggle();

            if (selectedRadio == radioBtnFilterTitle) {
                return media.getTitle().toLowerCase().contains(lowerCaseFilter);
            } else if (selectedRadio == radioBtnFilterId) {
                try {
                    int filterId = Integer.parseInt(lowerCaseFilter);
                    return media.getId() == filterId;
                } catch (NumberFormatException e) {
                    return false; 
                }
            }
            return false; 
        });

        tblMedia.setItems(filteredData);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}