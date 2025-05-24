package hust.soict.hedspi.aims.media;


import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {

    private static int nbDigitalVideoDiscs = 0; 

    public DigitalVideoDisc(String title) {
        super(++nbDigitalVideoDiscs, title, null, 0.0f, 0, null);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, length, director);
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

    @Override
    public void play() throws PlayerException { [cite: 215]
        if (this.getLength() > 0) { [cite: 212]
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD Length: " + this.getLength());
            // TODO: 
        } else {
            System.err.println("ERROR: DVD length is non-positive!"); [cite: 213]
            throw new PlayerException("ERROR: DVD length is non-positive!"); [cite: 213, 215]
        }
    }
}