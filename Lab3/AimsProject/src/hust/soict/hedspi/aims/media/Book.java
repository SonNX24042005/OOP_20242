package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {

    private List<String> authors = new ArrayList<>();

     public Book() {
        super();
    }

    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public Book(int id, String title, String category, float cost, List<String> authors) {
        super(id, title, category, cost);
        if (authors != null) {
            this.authors = new ArrayList<>(authors);
        }
    }

    public List<String> getAuthors() {
        return new ArrayList<>(authors);
    }

    public void addAuthor(String authorName) {
         if (authorName == null || authorName.trim().isEmpty()) {
             System.out.println("Lỗi: Tên tác giả không được để trống.");
             return;
        }
        boolean found = false;
        for (String existingAuthor : this.authors) {
            if (existingAuthor.equalsIgnoreCase(authorName.trim())) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.authors.add(authorName.trim());
            System.out.println("Đã thêm tác giả: " + authorName.trim());
        } else {
            System.out.println("Thông báo: Tác giả '" + authorName.trim() + "' đã tồn tại.");
        }
    }

    public void removeAuthor(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
             System.out.println("Lỗi: Tên tác giả cần xóa không được để trống.");
             return;
        }
        boolean removed = this.authors.removeIf(existingAuthor -> existingAuthor.equalsIgnoreCase(authorName.trim()));
        if (removed) {
            System.out.println("Đã xóa tác giả: " + authorName.trim());
        } else {
            System.out.println("Lỗi: Không tìm thấy tác giả '" + authorName.trim() + "' để xóa.");
        }
    }

    @Override
    public String toString() {
        return "Book [ID=" + getId() + ", Title='" + getTitle() + '\'' +
               ", Category='" + getCategory() + '\'' + ", Cost=" + getCost() +
               ", Authors=" + authors +
               ']';
    }
}