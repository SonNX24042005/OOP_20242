package hust.soict.hedspi.test.store;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class StoreTest {

    public static void main(String[] args) {
        // 1. Tạo cửa hàng mới
        Store myStore = new Store();

        // 2. Tạo một vài đối tượng DVD
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Matrix", "Science Fiction", "Wachowskis", 136, 22.50f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Inception", "Science Fiction", "Christopher Nolan", 148, 25.00f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Interstellar", "Science Fiction", "Christopher Nolan", 169, 28.99f);
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f); // DVD đã dùng ở test khác

        // 3. Test phương thức addDVD
        System.out.println("--- Testing addDVD ---");
        myStore.addDVD(dvd1);
        myStore.addDVD(dvd2);
        myStore.addDVD(dvd3);
        myStore.addDVD(dvd4);

        // Thử thêm lại DVD đã có
        System.out.println("\nAttempting to add existing DVD:");
        myStore.addDVD(dvd1);

        // Thử thêm DVD null
        System.out.println("\nAttempting to add null DVD:");
        myStore.addDVD(null);

        // In trạng thái cửa hàng sau khi thêm
        System.out.println("\nStore state after additions:");
        myStore.printStore();
        // Kiểm tra số lượng
        System.out.println("Expected item count: 4. Actual: " + myStore.getItemCount());


        // 4. Test phương thức removeDVD
        System.out.println("\n--- Testing removeDVD ---");

        // Xóa một DVD tồn tại
        System.out.println("\nRemoving DVD '" + dvd2.getTitle() + "':");
        myStore.removeDVD(dvd2);
        myStore.printStore();
        System.out.println("Expected item count: 3. Actual: " + myStore.getItemCount());

        // Thử xóa DVD không tồn tại trong cửa hàng
        System.out.println("\nAttempting to remove non-existent DVD (using dvd2 again):");
        myStore.removeDVD(dvd2); // dvd2 đã bị xóa
        myStore.printStore();
        System.out.println("Expected item count: 3. Actual: " + myStore.getItemCount());

        // Thử xóa DVD null
        System.out.println("\nAttempting to remove null DVD:");
        myStore.removeDVD(null);
        myStore.printStore();
        System.out.println("Expected item count: 3. Actual: " + myStore.getItemCount());

        // Xóa các DVD còn lại
        System.out.println("\nRemoving remaining DVDs:");
        myStore.removeDVD(dvd1);
        myStore.removeDVD(dvd4);
        myStore.removeDVD(dvd3);
        myStore.printStore();
        System.out.println("Expected item count: 0. Actual: " + myStore.getItemCount());

        // Thử xóa từ cửa hàng trống
        System.out.println("\nAttempting to remove from empty store:");
        myStore.removeDVD(dvd1); // dvd1 không còn trong cửa hàng
        myStore.printStore();
        System.out.println("Expected item count: 0. Actual: " + myStore.getItemCount());
    }
}