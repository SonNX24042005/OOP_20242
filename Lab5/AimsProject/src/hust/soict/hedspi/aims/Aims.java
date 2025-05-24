package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class AimsApplication extends Application {

    private static Store store;
    private static Cart cart;

    @Override
    public void start(Stage primaryStage) throws Exception { [cite: 76, 142]
        store = new Store(); [cite: 144]
        cart = new Cart();
        initializeStoreData(); 

        final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml"; [cite: 143]
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH)); [cite: 143]

        fxmlLoader.setController(new ViewStoreController(store, cart));

        Parent root = fxmlLoader.load(); [cite: 143]
        primaryStage.setTitle("Store"); [cite: 143]
        primaryStage.setScene(new Scene(root)); [cite: 143]
        primaryStage.show(); [cite: 143]
    }

    public static void main(String[] args) {
        launch(args); [cite: 79, 145]
    }

    // Hàm khởi tạo dữ liệu mẫu 
    private void initializeStoreData() {
        Book book1 = new Book(1, "The Lord of the Rings", "Fantasy", 25.50f, List.of("J.R.R. Tolkien"));
        Book book2 = new Book(2, "Pride and Prejudice", "Romance", 15.00f, List.of("Jane Austen"));

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Matrix", "Science Fiction", "Wachowskis", 136, 22.50f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Inception", "Science Fiction", "Christopher Nolan", 148, 25.00f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Interstellar", "Science Fiction", "Christopher Nolan", 169, 28.99f);

        CompactDisc cd1 = new CompactDisc(10, "Dark Side of the Moon", "Rock", 18.80f, null, "Pink Floyd");
        cd1.addTrack(new Track("Speak to Me/Breathe", 238));
        cd1.addTrack(new Track("Time", 413));
        cd1.addTrack(new Track("Money", 382));
        cd1.addTrack(new Track("Invalid Track", 0));


        CompactDisc cd2 = new CompactDisc(11, "Abbey Road", "Rock", 20.50f, null, "The Beatles");
        cd2.addTrack(new Track("Come Together", 260));
        cd2.addTrack(new Track("Something", 183));
        cd2.addTrack(new Track("Here Comes the Sun", 185));

        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Test DVD No Length", "Test", "Tester", 0, 5.0f);


        store.addMedia(book1, book2, dvd1, dvd2, dvd3, cd1, cd2, dvd4);
        System.out.println("Store initialized with sample media for JavaFX App.");
    }
}