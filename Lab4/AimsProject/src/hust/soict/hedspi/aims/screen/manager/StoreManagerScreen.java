package hust.soict.hedspi.aims.screen.manager;
import java.util.ArrayList;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import java.awt.*;
import javax.swing.*;
public class StoreManagerScreen extends JFrame {
	private Store store;
	public StoreManagerScreen(Store store) { // Constructor nhận đối tượng Store
	    this.store = store;

	    Container cp = getContentPane();
	    cp.setLayout(new BorderLayout());

	    // Thêm các thành phần NORTH và CENTER
	    cp.add(createNorth(), BorderLayout.NORTH);
	    cp.add(createCenter(), BorderLayout.CENTER);

	    // Thiết lập JFrame
	    setTitle("Store");
	    setSize(1024, 768); // Kích thước cửa sổ
	    setLocationRelativeTo(null); // Căn giữa màn hình
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Thoát khi đóng
	    setVisible(true); // Hiển thị cửa sổ
	}
	JPanel createNorth() {
	    JPanel north = new JPanel();
	    // Sắp xếp menu bar và header theo chiều dọc
	    north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
	    north.add(createMenuBar()); // Thêm thanh menu
	    north.add(createHeader());  // Thêm header
	    return north;
	}
	JMenuBar createMenuBar() {
	    JMenu menu = new JMenu("Options"); // Menu chính

	    // Mục menu con "View store"
	    menu.add(new JMenuItem("View store"));

	    // Menu con "Update Store"
	    JMenu smUpdateStore = new JMenu("Update Store");
	    JMenuItem addBook = new JMenuItem("Add Book");
	    addBook.addActionListener(e -> new AddBookToStoreScreen(store));

	    JMenuItem addCD = new JMenuItem("Add CD");
	    addCD.addActionListener(e -> new AddCompactDiscToStoreScreen(store));

	    JMenuItem addDVD = new JMenuItem("Add DVD");
	    addDVD.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store));
	    
	    smUpdateStore.add(addBook);
	    smUpdateStore.add(addCD);
	    smUpdateStore.add(addDVD);

	    JMenuItem viewStore = new JMenuItem("View store");
	    viewStore.addActionListener(e -> {
	        this.setVisible(false); // ẩn màn hình hiện tại
	        new StoreManagerScreen(store); // mở lại màn hình chính
	    });
	    menu.add(viewStore);
	    menu.add(smUpdateStore); // Thêm menu con vào menu chính

	    // Tạo thanh menu và thêm menu chính vào
	    JMenuBar menuBar = new JMenuBar();
	    menuBar.setLayout(new FlowLayout(FlowLayout.LEFT)); // Căn lề trái
	    menuBar.add(menu);

	    return menuBar;
	}
	JPanel createHeader() {
	    JPanel header = new JPanel();
	    // Sắp xếp các thành phần header theo chiều ngang
	    header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

	    // Tiêu đề "AIMS"
	    JLabel title = new JLabel("AIMS");
	    title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50)); // Font lớn
	    title.setForeground(Color.CYAN); // Màu chữ

	    // Thêm khoảng trống -> Tiêu đề -> Keo dãn -> Khoảng trống
	    header.add(Box.createRigidArea(new Dimension(10, 10)));
	    header.add(title);
	    header.add(Box.createHorizontalGlue()); // Đẩy các thành phần sang hai bên
	    header.add(Box.createRigidArea(new Dimension(10, 10)));

	    return header;
	}
	JPanel createCenter() {

	    JPanel center = new JPanel();
	    center.setLayout(new GridLayout(3, 3, 2, 2));

	    ArrayList<Media> mediaInStore = store.getItemsInStore();
	    for (int i = 0; i < 9; i++) {
	        MediaStore cell = new MediaStore(mediaInStore.get(i));
	        center.add(cell);
	    }

	    return center;
	}
	public static void main(String[] args) {
	    Store store = new Store();

	    // Thêm các sản phẩm vào cửa hàng
	    store.addMedia(new Book(1, "The Great Gatsby", "Novel", 10.99f));
	    store.addMedia(new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 19.99f));
	    store.addMedia(new CompactDisc(3, "Adele 30", "Pop", 15.50f, "John Smith", "Adele"));
	    store.addMedia(new DigitalVideoDisc("The Matrix", "Action", "Wachowski Brothers", 136, 14.50f));
	    store.addMedia(new Book(5, "1984", "Dystopian", 9.99f));
	    store.addMedia(new CompactDisc(6, "Linkin Park", "Rock", 12.25f, "Rick Rubin", "Linkin Park"));
	    store.addMedia(new Book(7, "To Kill a Mockingbird", "Classic", 8.99f));
	    store.addMedia(new DigitalVideoDisc("Avengers", "Superhero", "Joss Whedon", 143, 17.99f));
	    store.addMedia(new CompactDisc(9, "Coldplay", "Alternative", 13.49f, "Brian Eno", "Coldplay"));

	    // Khởi chạy giao diện quản lý cửa hàng
	    new StoreManagerScreen(store);
	}

	
}
