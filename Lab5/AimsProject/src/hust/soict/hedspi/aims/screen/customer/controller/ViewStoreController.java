package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewStoreController {

    private Store store;
    private Cart cart;

    @FXML
    private GridPane gridPane; [cite: 114]

    public ViewStoreController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() { [cite: 132]
        final String ITEM_FXML_FILE_PATH = "/hust/soict/dsai/aims/screen/customer/view/Item.fxml";
        int column = 0;
        int row = 1;

        if (store != null && store.getItemsInStore() != null) {
            for (int i = 0; i < store.getItemsInStore().size(); i++) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource(ITEM_FXML_FILE_PATH)); [cite: 134]

                    ItemController itemController = new ItemController(cart); [cite: 134]
                    fxmlLoader.setController(itemController); [cite: 134]

                    AnchorPane anchorPane = fxmlLoader.load(); [cite: 134]
                    itemController.setData(store.getItemsInStore().get(i)); [cite: 134]

                    if (column == 3) { [cite: 135]
                        column = 0;
                        row++; [cite: 135]
                    }

                    gridPane.add(anchorPane, column++, row); [cite: 135]
                    GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10)); [cite: 135]

                } catch (IOException e) { [cite: 135]
                    e.printStackTrace();
                }
            }
        }
    }


    @FXML
    void btnViewCartPressed(ActionEvent event) { [cite: 199]
        try {
            final String CART_FXML_FILE_PATH = "/hust/soict/dsai/aims/screen/customer/view/Cart.fxml"; [cite: 199]
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH)); [cite: 199]

            fxmlLoader.setController(new CartController(store, cart)); [cite: 200]

            Parent root = fxmlLoader.load(); [cite: 200]
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); [cite: 201, 202]
            stage.setScene(new Scene(root)); [cite: 203]
            stage.setTitle("Cart"); [cite: 203]
            stage.show(); [cite: 203]

        } catch (IOException e) { [cite: 199]
            e.printStackTrace();
        }
    }
}