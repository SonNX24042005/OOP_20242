package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart; // Import Cart
import hust.soict.hedspi.aims.media.Media;
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
import java.util.ArrayList;

public class ViewStoreController {

    private Store store;
    private Cart cart; // Thêm thuộc tính Cart

    @FXML
    private GridPane gridPane;

    // Cập nhật constructor để nhận cả Store và Cart
    public ViewStoreController(Store store, Cart cart) { //
        this.store = store;
        this.cart = cart; // Khởi tạo cart
    }

    @FXML
    public void initialize() {
        final String ITEM_FXML_FILE_PATH = "/hust/soict/globalict/dsai/aims/screen/customer/view/Item.fxml";
        ArrayList<Media> itemsInStore = store.getItemsInStore();
        int column = 0;
        int row = 1;

        if (itemsInStore == null) {
            System.err.println("Store items are null!");
            return;
        }
        
        for (Media mediaItem : itemsInStore) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ITEM_FXML_FILE_PATH));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                // Truyền cart cho ItemController để nó có thể thêm item vào cart
                itemController.setData(mediaItem, this.cart); // Sửa đổi setData để nhận Cart

                if (column == 3) {
                    column = 0;
                    row++;
                }
                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnViewCartPressed(ActionEvent event) { //
        try {
            final String CART_FXML_FILE_PATH = "/hust/soict/globalict/dsai/aims/screen/customer/view/Cart.fxml"; //
            FXMLLoader loader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH)); //
            
            // Tạo CartController và truyền cả cart và store
            // Lưu ý: Hình ảnh hướng dẫn ban đầu (Fig 47) chỉ truyền cart.
            // Tuy nhiên, để CartController có thể quay lại StoreScreen và truyền Store,
            // CartController cũng cần biết về Store.
            // Theo "Note" trong image_767613.png, CartController sẽ được sửa đổi để nhận cả store và cart.
            CartController cartController = new CartController(this.cart, this.store); //
            loader.setController(cartController); //
            
            Parent root = loader.load(); //
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //
            stage.setScene(new Scene(root)); //
            stage.setTitle("Cart"); //
            stage.show(); //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}