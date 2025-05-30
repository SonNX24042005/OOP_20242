package hust.soict.hedspi.aims.screen.customer.controller;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox; // Static methods from HBox can be used directly

public class ItemController {

    private Media media;
    private Cart cart;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private HBox buttonsBox; // fx:id added to HBox in Item.fxml for this
    



    public void setData(Media media, Cart cart) {
        this.media = media;
        this.cart = cart; // Gán tham số cart cho thuộc tính this.cart

        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + " $");
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
            // Xử lý layout nếu cần khi nút Play ẩn
        }
    }

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        if (this.cart != null && this.media != null) {
            this.cart.addMedia(media);
            // Hiển thị thông báo hoặc cập nhật UI nếu cần
            System.out.println("Added to cart: " + media.getTitle() + ". Items in cart: " + this.cart.getItemsCount());
             javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle("Item Added");
            alert.setHeaderText(null);
            alert.setContentText(media.getTitle() + " has been added to your cart.");
            alert.showAndWait();
        } else {
            System.err.println("Cart or Media is null in ItemController. Cannot add to cart.");
        }
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        if (media instanceof Playable) {
            System.out.println("Playing: " + media.getTitle());
            ((Playable) media).play();
            // Optionally show a dialog or some visual feedback in the GUI
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle("Playing Media");
            alert.setHeaderText(null);
            alert.setContentText("Playing: " + media.getTitle());
            alert.showAndWait();
        }
    }
}