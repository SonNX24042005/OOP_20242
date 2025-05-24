package hust.soict.hedspi.aims.media;


import hust.soict.hedspi.aims.exception.PlayerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class CompactDisc extends Disc implements Playable {

    private String artist;
    private List<Track> tracks = new ArrayList<>();

    // Constructor giữ nguyên...
    public CompactDisc(int id, String title, String category, float cost, String director, String artist) {
        super(id, title, category, cost, 0, director);
        this.artist = artist;
    }

    public CompactDisc(int id, String title, String category, float cost, String director, String artist, List<Track> tracks) {
        super(id, title, category, cost, 0, director);
        this.artist = artist;
        if (tracks != null) {
             for(Track t : tracks) {
                 this.addTrack(t);
             }
        }
    }


    public String getArtist() { return artist; }
    public void addTrack(Track track) { /* ... */ }
    public void removeTrack(Track track) { /* ... */ }


    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException { [cite: 219, 222]
        if (this.getLength() <= 0) { [cite: 218]
            throw new PlayerException("ERROR: CD length is non-positive!"); [cite: 224]
        }

        System.out.println("------------------------------------");
        System.out.println("Playing CD: " + this.getTitle() + " by " + this.artist);

        Iterator<Track> iter = tracks.iterator(); [cite: 223]
        Track nextTrack;
        boolean playedSomething = false;

        while(iter.hasNext()) { [cite: 223]
            nextTrack = iter.next(); [cite: 223]
            try {
                nextTrack.play(); [cite: 223]
                playedSomething = true;
            } catch(PlayerException e) { [cite: 220]
                System.err.println("Error playing track " + nextTrack.getTitle() + ": " + e.getMessage());
                 throw e; [cite: 223] 
            }
        }

        if (!playedSomething && tracks.isEmpty()) {
             throw new PlayerException("ERROR: CD has no tracks to play!");
        } else if (!playedSomething) {
             throw new PlayerException("ERROR: No tracks in the CD could be played!");
        }
        System.out.println("------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("CD [%d] - %s - %s - %s - %s - %d: %.2f $ (Tracks: %d)",
                getId(), getTitle(), getCategory(), getDirector(), artist, getLength(), getCost(), tracks.size());
    }

    public List<Track> getTracks() {
        return tracks;
    }
}