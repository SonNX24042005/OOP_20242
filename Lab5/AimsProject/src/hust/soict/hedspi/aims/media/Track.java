package hust.soict.hedspi.aims.media;


import hust.soict.hedspi.aims.exception.PlayerException;
import java.util.Objects;

public class Track implements Playable {

    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
         if (length >= 0) {
            this.length = length;
        } else {
            this.length = 0;
            System.out.println("Warning: Track length cannot be negative. Setting length for '" + title + "' to 0.");
        }
    }
    public String getTitle() { return title; }
    public int getLength() { return length; }

    @Override
    public void play() throws PlayerException { [cite: 215] 
        if (this.getLength() > 0) { [cite: 212]
            System.out.println("Playing Track: " + this.getTitle());
            System.out.println("Track Length: " + this.getLength());
        } else {
            System.err.println("ERROR: Track length (" + this.getTitle() + ") is non-positive!"); [cite: 213]
            throw new PlayerException("ERROR: Track length (" + this.getTitle() + ") is non-positive!"); [cite: 213, 215]
        }
    }

    @Override
    public String toString() {
        return "Track: " + this.getTitle() + " - Length: " + this.getLength() + "s";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return this.getLength() == track.getLength() &&
               Objects.equals(this.getTitle(), track.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTitle(), this.getLength());
    }
}