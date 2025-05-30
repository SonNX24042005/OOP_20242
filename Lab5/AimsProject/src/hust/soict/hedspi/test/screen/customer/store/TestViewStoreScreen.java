package hust.soict.hedspi.test.screen.customer.store;

import hust.soict.hedspi.aims.cart.Cart; // Import Cart
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

import java.util.List; // Cần cho List.of

public class TestViewStoreScreen extends Application {

    private static Store store;
    private static Cart cart; // Thêm đối tượng Cart

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        
        // Truyền cả store và cart cho ViewStoreController
        ViewStoreController viewStoreController = new ViewStoreController(store, cart);
        fxmlLoader.setController(viewStoreController);
        
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        store = new Store();
        cart = new Cart(); // Khởi tạo cart
        initializeStoreWithSampleData(); // Thêm dữ liệu mẫu (bao gồm cả việc thêm vào store)
        launch(args);
    }

    private static void initializeStoreWithSampleData() {
        Book book1 = new Book(1, "Harry Potter and the Philosopher's Stone (2001)", "Fantasy", 3.0f, List.of("J.K. Rowling"));
        store.addMedia(book1);
        Book book2 = new Book(2, "Harry Potter and the Chamber of Secrets (2002)", "Fantasy", 3.5f, List.of("J.K. Rowling"));
        store.addMedia(book2);
        Book book3 = new Book(3, "Harry Potter and the Prisoner of Azkaban (2004)", "Fantasy", 5.0f, List.of("J.K. Rowling"));
        store.addMedia(book3);

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Harry Potter and the Goblet of Fire (2005)", "Fantasy", "Mike Newell", 157, 4.5f);
        store.addMedia(dvd1);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Harry Potter and the Order of the Phoenix (2007)", "Fantasy", "David Yates", 138, 5.5f);
        store.addMedia(dvd2);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Harry Potter and the Half-Blood Prince (2009)", "Fantasy", "David Yates", 153, 5.8f);
        store.addMedia(dvd3);
        
        CompactDisc cd1 = new CompactDisc(7, "Harry Potter and the Deathly Hallows – Part 1 (2010)", "Fantasy", 6.3f, "David Yates", "Various Artists");
        cd1.addTrack(new Track("Obliviate", 180));
        cd1.addTrack(new Track("Snape to Malfoy Manor", 120));
        store.addMedia(cd1);

        CompactDisc cd2 = new CompactDisc(8, "Harry Potter and the Deathly Hallows – Part 2 (2011)", "Fantasy", 7.0f, "David Yates", "Various Artists");
        cd2.addTrack(new Track("Lily's Theme", 150));
        cd2.addTrack(new Track("Statues", 140));
        store.addMedia(cd2);

        Book book4 = new Book(9, "Green Eggs and Ham", "Children's", 3.3f, List.of("Dr. Seuss"));
        store.addMedia(book4);

        System.out.println("Store initialized with sample media for testing TestViewStoreScreen.");
    }
}