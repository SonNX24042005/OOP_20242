package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class DigitalVideoDisc extends Disc implements Playable {

    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc(String title) {
        super(++nbDigitalVideoDiscs, title, null, 0.0f, 0, null);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, length, director);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, 0, director);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, 0, null);
    }

    @Override
    public String toString() {
        return String.format("DVD [%d] - %s - %s - %s - %d: %.2f $",
                getId(),
                getTitle(),
                (getCategory() != null ? getCategory() : "N/A"),
                (getDirector() != null ? getDirector() : "N/A"),
                getLength(),
                getCost());
    }

     public boolean isMatch(String titleToMatch) {
        if (titleToMatch == null || this.getTitle() == null) {
            return false;
        }
        return this.getTitle().toLowerCase().contains(titleToMatch.toLowerCase());
    }

     public void play() {
         if (this.getLength() > 0) {
             System.out.println("Playing DVD: " + this.getTitle());
             System.out.println("DVD Length: " + this.getLength());
         } else {
             System.out.println("Cannot play DVD: " + this.getTitle() + " - Length is zero or less.");
         }
     }
}