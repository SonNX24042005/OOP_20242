package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Store {

    private ArrayList<Media> itemsInStore = new ArrayList<>();

    public Store() {

    }

    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("Lỗi: Không thể thêm đối tượng null vào cửa hàng.");
            return;
        }
        if (itemsInStore.contains(media)) {
            System.out.println("Thông báo: Sản phẩm '" + media.getTitle() + "' (ID: " + media.getId() + ") đã có trong cửa hàng.");
        } else {
            itemsInStore.add(media);
            System.out.println("Đã thêm '" + media.getTitle() + "' vào cửa hàng.");
        }
    }

    public void addMedia(Media... mediaList) {
        if (mediaList == null) return;
        for (Media media : mediaList) {
            addMedia(media);
        }
    }

    public void removeMedia(Media media) {
        if (media == null) {
            System.out.println("Lỗi: Không thể xóa đối tượng null khỏi cửa hàng.");
            return;
        }
        boolean removed = itemsInStore.remove(media);

        if (removed) {
            System.out.println("Đã xóa '" + media.getTitle() + "' (ID: " + media.getId() + ") khỏi cửa hàng.");
        } else {
            System.out.println("Lỗi: Không tìm thấy sản phẩm '" + media.getTitle() + "' (ID: " + media.getId() + ") trong cửa hàng để xóa.");
        }
    }

    public void printStore() {
        System.out.println("***********************STORE***********************");
        if (itemsInStore.isEmpty()) {
            System.out.println("Cửa hàng hiện không có sản phẩm nào.");
        } else {
            System.out.println("Sản phẩm hiện có:");

            for (int i = 0; i < itemsInStore.size(); i++) {
                Media media = itemsInStore.get(i);
                System.out.println((i + 1) + ". " + media.toString());
            }
        }
        System.out.println("***************************************************");
    }

    public int getItemsCount() {
        return itemsInStore.size();
    }

     public Media searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return null;
        }
        String searchLower = title.trim().toLowerCase();
        for (Media media : itemsInStore) {
            if (media != null && media.getTitle() != null && media.getTitle().toLowerCase().contains(searchLower)) {
                return media;
            }
        }
        return null;
    }

     public Media searchById(int id) {
         for (Media media : itemsInStore) {
             if (media != null && media.getId() == id) {
                 return media;
             }
         }
         return null;
     }

    public ArrayList<Media> getItemsInStore() {
        return new ArrayList<>(itemsInStore);
    }

}