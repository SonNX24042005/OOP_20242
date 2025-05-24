package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
    }

    @Override
    protected JPanel createForm() {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        JTextField tfTitle = new JTextField();
        JTextField tfCategory = new JTextField();
        JTextField tfCost = new JTextField();
        JTextField tfDirector = new JTextField();
        JTextField tfArtist = new JTextField();

        panel.add(new JLabel("Title:"));
        panel.add(tfTitle);

        panel.add(new JLabel("Category:"));
        panel.add(tfCategory);

        panel.add(new JLabel("Cost:"));
        panel.add(tfCost);

        panel.add(new JLabel("Director:"));
        panel.add(tfDirector);

        panel.add(new JLabel("Artist:"));
        panel.add(tfArtist);

        JButton btnAdd = new JButton("Add CD");
        btnAdd.addActionListener(e -> {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String director = tfDirector.getText();
            String artist = tfArtist.getText();

            int id = store.getItemsInStore().size() + 1;
            CompactDisc cd = new CompactDisc(id, title, category, cost, director, artist);
            store.addMedia(cd);
            JOptionPane.showMessageDialog(this, "CD added successfully!");
            this.dispose();
        });

        panel.add(new JLabel());
        panel.add(btnAdd);

        return panel;
    }
}
