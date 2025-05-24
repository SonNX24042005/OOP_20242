package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemController {

    private Media media;
    private Cart cart;

    @FXML
    private Button btnAddToCart; [cite: 120]

    @FXML
    private Button btnPlay; [cite: 120]

    @FXML
    private Label lblCost; [cite: 119]

    @FXML
    private Label lblTitle; [cite: 119]

    public ItemController(Cart cart) {
        this.cart = cart;
    }


    public void setData(Media media) { [cite: 124]
        this.media = media;
        lblTitle.setText(media.getTitle()); [cite: 124]
        lblCost.setText(String.format("%.2f $", media.getCost())); [cite: 124]

        if (media instanceof Playable) { [cite: 124]
            btnPlay.setVisible(true); [cite: 124]
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 30)); 
        } else {
            btnPlay.setVisible(false); [cite: 124]
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60)); [cite: 124]
        }
    }

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        try {
            cart.addMedia(media);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Media '" + media.getTitle() + "' added to cart.");
        } catch (LimitExceededException e) {
            showAlert(Alert.AlertType.ERROR, "Cart Full", e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
                 showAlert(Alert.AlertType.INFORMATION, "Playing",
                        "Playing: " + media.getTitle() + "\nLength: " + ((Playable) media).getLength() + "s");

            } catch (PlayerException e) {
                 showAlert(Alert.AlertType.ERROR, "Playback Error", e.getMessage()); [cite: 231, 232]
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}