package hust.soict.hedspi.aims.media;


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
    public void play() {  }
    @Override public String toString() {
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