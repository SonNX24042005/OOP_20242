package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
    }

    @Override
    protected JPanel createForm() {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        JTextField tfTitle = new JTextField();
        JTextField tfCategory = new JTextField();
        JTextField tfCost = new JTextField();
        JTextField tfDirector = new JTextField();
        JTextField tfLength = new JTextField();

        panel.add(new JLabel("Title:"));
        panel.add(tfTitle);

        panel.add(new JLabel("Category:"));
        panel.add(tfCategory);

        panel.add(new JLabel("Cost:"));
        panel.add(tfCost);

        panel.add(new JLabel("Director:"));
        panel.add(tfDirector);

        panel.add(new JLabel("Length (minutes):"));
        panel.add(tfLength);

        JButton btnAdd = new JButton("Add DVD");
        btnAdd.addActionListener(e -> {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String director = tfDirector.getText();
            int length = Integer.parseInt(tfLength.getText());

            DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
            store.addMedia(dvd);
            JOptionPane.showMessageDialog(this, "DVD added successfully!");
            this.dispose();
        });

        panel.add(new JLabel());
        panel.add(btnAdd);

        return panel;
    }
}
