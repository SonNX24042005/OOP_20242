package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Cart {

    private ArrayList<Media> itemsOrdered = new ArrayList<>();


    public ArrayList<Media> getItemsOrdered() {
        return new ArrayList<>(this.itemsOrdered);
    }
    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("Lỗi: Không thể thêm đối tượng null vào giỏ hàng.");
            return;
        }
        if (itemsOrdered.contains(media)) {
            System.out.println("Thông báo: Mục '" + media.getTitle() + "' (ID: " + media.getId() + ") đã có trong giỏ hàng.");
        } else {
            itemsOrdered.add(media);
            System.out.println("Đã thêm '" + media.getTitle() + "' vào giỏ hàng.");
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
            System.out.println("Lỗi: Không thể xóa đối tượng null khỏi giỏ hàng.");
            return;
        }
        boolean removed = itemsOrdered.remove(media);

        if (removed) {
            System.out.println("Đã xóa '" + media.getTitle() + "' (ID: " + media.getId() + ") khỏi giỏ hàng.");
        } else {
            System.out.println("Lỗi: Không tìm thấy mục '" + media.getTitle() + "' (ID: " + media.getId() + ") trong giỏ hàng để xóa.");
        }
    }


    public float totalCost() {
        float total = 0.0f;
        for (Media media : itemsOrdered) {
            if (media != null) {
                total += media.getCost();
            }
        }
        return total;
    }

    public void printCart() {
        System.out.println("***********************CART***********************");
        if (itemsOrdered.isEmpty()) {
            System.out.println("Giỏ hàng trống.");
        } else {
            System.out.println("Ordered Items (theo thứ tự thêm vào):");
            for (int i = 0; i < itemsOrdered.size(); i++) {
                Media item = itemsOrdered.get(i);
                if (item != null) {
                     System.out.println((i + 1) + ". " + item.toString());
                } else {
                     System.out.println((i + 1) + ". [LỖI: Mục bị null]");
                }
            }
        }
        System.out.printf("Total cost: %.2f $\n", totalCost());
        System.out.println("***************************************************");
    }
    public void printSortedByTitleCost() {
        if (itemsOrdered.isEmpty()) {
            printCart();
            return;
        }
        ArrayList<Media> sortedList = new ArrayList<>(itemsOrdered);
        Collections.sort(sortedList, Media.COMPARE_BY_TITLE_COST);

        System.out.println("******************CART (Sorted by Title/Cost)******************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < sortedList.size(); i++) {
            System.out.println((i + 1) + ". " + sortedList.get(i).toString());
        }
        System.out.printf("Total cost: %.2f $\n", totalCost());
        System.out.println("**************************************************************");
    }

     public void printSortedByCostTitle() {
        if (itemsOrdered.isEmpty()) {
            printCart();
            return;
        }
        ArrayList<Media> sortedList = new ArrayList<>(itemsOrdered);
        Collections.sort(sortedList, Media.COMPARE_BY_COST_TITLE);

        System.out.println("******************CART (Sorted by Cost/Title)******************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < sortedList.size(); i++) {
            System.out.println((i + 1) + ". " + sortedList.get(i).toString());
        }
        System.out.printf("Total cost: %.2f $\n", totalCost());
        System.out.println("**************************************************************");
    }

    public void searchById(int id) {
        System.out.println("--- Tìm kiếm Media theo ID: " + id + " ---");
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media != null && media.getId() == id) {
                System.out.println("Tìm thấy kết quả khớp:");
                System.out.println(media.toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy Media nào có ID = " + id + " trong giỏ hàng.");
        }
        System.out.println("--- Kết thúc tìm kiếm theo ID ---");
    }

    public void searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Lỗi: Tiêu đề tìm kiếm không được để trống.");
            return;
        }
        String searchLower = title.trim().toLowerCase();
        System.out.println("--- Tìm kiếm Media theo tiêu đề chứa: '" + title.trim() + "' ---");
        ArrayList<Media> results = new ArrayList<>();

        for (Media media : itemsOrdered) {
            if (media != null && media.getTitle() != null && media.getTitle().toLowerCase().contains(searchLower)) {
                results.add(media);
            }
        }

        if (results.isEmpty()) {
            System.out.println("Không tìm thấy Media nào có tiêu đề khớp với '" + title.trim() + "' trong giỏ hàng.");
        } else {
            System.out.println("Tìm thấy " + results.size() + " kết quả khớp:");
            int count = 1;
            for (Media media : results) {
                System.out.println(count + ". " + media.toString());
                count++;
            }
        }
        System.out.println("--- Kết thúc tìm kiếm theo tiêu đề ---");
    }

    public int getItemsCount() {
        return itemsOrdered.size();
    }

    public void emptyCart() {
        itemsOrdered.clear();
        System.out.println("Giỏ hàng đã được làm rỗng.");
    }
}