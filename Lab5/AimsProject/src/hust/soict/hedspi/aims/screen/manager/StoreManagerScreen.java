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
	public StoreManagerScreen(Store store) { 
	    this.store = store;

	    Container cp = getContentPane();
	    cp.setLayout(new BorderLayout());

	    cp.add(createNorth(), BorderLayout.NORTH);
	    cp.add(createCenter(), BorderLayout.CENTER);

	    setTitle("Store");
	    setSize(1024, 768); 
	    setLocationRelativeTo(null)
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    setVisible(true); 
	}
	JPanel createNorth() {
	    JPanel north = new JPanel();
	    north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
	    north.add(createMenuBar());
	    north.add(createHeader());  
	    return north;
	}
	JMenuBar createMenuBar() {
	    JMenu menu = new JMenu("Options"); 
	    menu.add(new JMenuItem("View store"));

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
	        this.setVisible(false); 
	        new StoreManagerScreen(store); 
	    });
	    menu.add(viewStore);
	    menu.add(smUpdateStore); 

	    JMenuBar menuBar = new JMenuBar();
	    menuBar.setLayout(new FlowLayout(FlowLayout.LEFT)); 
	    menuBar.add(menu);

	    return menuBar;
	}
	JPanel createHeader() {
	    JPanel header = new JPanel();
	    
	    header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

	   
	    JLabel title = new JLabel("AIMS");
	    title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
	    title.setForeground(Color.CYAN); 
	    
	    header.add(Box.createRigidArea(new Dimension(10, 10)));
	    header.add(title);
	    header.add(Box.createHorizontalGlue()); 
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

	    store.addMedia(new Book(1, "The Great Gatsby", "Novel", 10.99f));
	    store.addMedia(new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 19.99f));
	    store.addMedia(new CompactDisc(3, "Adele 30", "Pop", 15.50f, "John Smith", "Adele"));
	    store.addMedia(new DigitalVideoDisc("The Matrix", "Action", "Wachowski Brothers", 136, 14.50f));
	    store.addMedia(new Book(5, "1984", "Dystopian", 9.99f));
	    store.addMedia(new CompactDisc(6, "Linkin Park", "Rock", 12.25f, "Rick Rubin", "Linkin Park"));
	    store.addMedia(new Book(7, "To Kill a Mockingbird", "Classic", 8.99f));
	    store.addMedia(new DigitalVideoDisc("Avengers", "Superhero", "Joss Whedon", 143, 17.99f));
	    store.addMedia(new CompactDisc(9, "Coldplay", "Alternative", 13.49f, "Brian Eno", "Coldplay"));

	    new StoreManagerScreen(store);
	}

	
}
