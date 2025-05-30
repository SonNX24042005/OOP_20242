package hust.soict.hedspi.javafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PainterController {

    @FXML
    private RadioButton eraser;

    @FXML
    private RadioButton pen;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane drawingAreaPane;

    // Xử lý khi người dùng nhấn nút "Clear"
    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    // Xử lý khi người dùng kéo chuột để vẽ hoặc xóa
    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        // Giới hạn khu vực vẽ trong Pane
        Rectangle clipArea = new Rectangle(0, 0, drawingAreaPane.getWidth(), drawingAreaPane.getHeight());
        drawingAreaPane.setClip(clipArea);

        // Mặc định màu vẽ là đen
        Color inkColor = Color.BLACK;

        // Nếu chọn "eraser" thì đổi sang màu trắng (giống như tẩy)
        if (eraser.isSelected()) {
            inkColor = Color.WHITE;
        }

        // Tạo vòng tròn nhỏ tại vị trí chuột kéo
        Circle newCircle = new Circle(event.getX(), event.getY(), 4, inkColor);
        drawingAreaPane.getChildren().add(newCircle);
    }
}
