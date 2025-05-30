package hust.soict.hedspi.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Painter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Tải giao diện từ file FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/hedspi/javafx/Painter.fxml"));
        Parent root = loader.load();

        // Tạo scene và hiển thị cửa sổ chính
        Scene scene = new Scene(root);
        primaryStage.setTitle("Painter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
