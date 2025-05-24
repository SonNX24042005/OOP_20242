package hust.soict.hedspi.aims.cart;

package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;

public class Cart {

    public static final int MAX_NUMBERS_ORDERED = 20; 
    private ObservableList<Media> itemsOrdered =
            FXCollections.observableArrayList(); [cite: 175]

    public ObservableList<Media> getItemsOrdered() { [cite: 174]
        return itemsOrdered;
    }

    public void addMedia(Media media) throws LimitExceededException { [cite: 210]
        if (media == null) {
            System.out.println("Lỗi: Không thể thêm đối tượng null vào giỏ hàng.");
            return;
        }
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) { [cite: 210]
            if (itemsOrdered.contains(media)) {
                System.out.println("Thông báo: Mục '" + media.getTitle() + "' đã có trong giỏ hàng.");
            } else {
                itemsOrdered.add(media);
                System.out.println("Đã thêm '" + media.getTitle() + "' vào giỏ hàng.");
            }
        } else {
            throw new LimitExceededException("ERROR: The number of media has reached its limit (" + MAX_NUMBERS_ORDERED + ")"); [cite: 210]
        }
    }

    public void addMedia(Media... mediaList) throws LimitExceededException {
         if (mediaList == null) return;
         for (Media media : mediaList) {
             addMedia(media);
         }
     }

    public void removeMedia(Media media) { [cite: 188]
        if (media == null) {
            System.out.println("Lỗi: Không thể xóa đối tượng null khỏi giỏ hàng.");
            return;
        }
        boolean removed = itemsOrdered.remove(media);

        if (removed) {
            System.out.println("Đã xóa '" + media.getTitle() + "' khỏi giỏ hàng.");
        } else {
            System.out.println("Lỗi: Không tìm thấy mục '" + media.getTitle() + "' trong giỏ hàng để xóa.");
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

    public int getItemsCount() {
        return itemsOrdered.size();
    }

    public void emptyCart() {
        itemsOrdered.clear();
        System.out.println("Giỏ hàng đã được làm rỗng.");
    }


}