package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class CartTest {

    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        cart.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 124, 24.95f);
        cart.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin",
                "Animation", 18.99f);
        cart.addDigitalVideoDisc(dvd3);

        System.out.println("--- Testing printCart() method ---");
        cart.printCart();


        System.out.println("\n--- Testing search methods ---");

        System.out.println("\n--- Testing searchById() ---");
        int idToSearch1 = dvd1.getId();
        System.out.println("Searching for ID: " + idToSearch1);
        cart.searchById(idToSearch1);

        int idToSearch3 = dvd3.getId();
        System.out.println("\nSearching for ID: " + idToSearch3);
        cart.searchById(idToSearch3);

        System.out.println("\nSearching for non-existent ID: 99");
        cart.searchById(99);

        System.out.println("\n--- Testing searchByTitle() ---");
        String titleToSearch1 = "Lion King";
        System.out.println("\nSearching for title containing: '" + titleToSearch1 + "'");
        cart.searchByTitle(titleToSearch1);

        String titleToSearch2 = "wars";
        System.out.println("\nSearching for title containing: '" + titleToSearch2 + "' (case-insensitive)");
        cart.searchByTitle(titleToSearch2);

        String titleToSearch3 = "Aladdin";
        System.out.println("\nSearching for title containing: '" + titleToSearch3 + "'");
        cart.searchByTitle(titleToSearch3);

        String titleToSearch4 = "NonExistent";
        System.out.println("\nSearching for title containing: '" + titleToSearch4 + "'");
        cart.searchByTitle(titleToSearch4);
    }
}