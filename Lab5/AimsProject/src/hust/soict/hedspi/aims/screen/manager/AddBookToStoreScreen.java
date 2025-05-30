package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    public AddBookToStoreScreen(Store store) {
        super(store);
    }

    @Override
    protected JPanel createForm() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JTextField tfTitle = new JTextField();
        JTextField tfCategory = new JTextField();
        JTextField tfCost = new JTextField();
        JTextField tfAuthors = new JTextField(); // cách nhau bởi dấu phẩy

        panel.add(new JLabel("Title:"));
        panel.add(tfTitle);

        panel.add(new JLabel("Category:"));
        panel.add(tfCategory);

        panel.add(new JLabel("Cost:"));
        panel.add(tfCost);

        panel.add(new JLabel("Authors (comma separated):"));
        panel.add(tfAuthors);

        JButton btnAdd = new JButton("Add Book");
        btnAdd.addActionListener(e -> {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String[] authorsArr = tfAuthors.getText().split(",");
            List<String> authors = new ArrayList<>();
            for (String s : authorsArr) authors.add(s.trim());

            Book book = new Book(store.getItemsInStore().size() + 1, title, category, cost, authors);
            store.addMedia(book);
            JOptionPane.showMessageDialog(this, "Book added!");
            this.dispose();
        });

        panel.add(new JLabel());
        panel.add(btnAdd);

        return panel;
    }
}
