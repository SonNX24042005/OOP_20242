package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable {

    private String artist;
    private List<Track> tracks = new ArrayList<>();

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

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (track == null) {
            System.out.println("Lỗi: Không thể thêm track null.");
            return;
        }
        if (tracks.contains(track)) {
            System.out.println("Thông báo: Track '" + track.getTitle() + "' đã tồn tại trong CD.");
        } else {
            tracks.add(track);
            System.out.println("Đã thêm track: " + track.getTitle());
        }
    }

    public void removeTrack(Track track) {
        if (track == null) {
            System.out.println("Lỗi: Không thể xóa track null.");
            return;
        }
        boolean removed = tracks.remove(track);
        if (removed) {
            System.out.println("Đã xóa track: " + track.getTitle());
        } else {
            System.out.println("Lỗi: Không tìm thấy track '" + track.getTitle() + "' để xóa.");
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() {
        if (tracks.isEmpty()) {
            System.out.println("Cannot play CD: " + this.getTitle() + " - No tracks available.");
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("Playing CD: " + this.getTitle() + " by " + this.artist);
        System.out.println("CD Category: " + this.getCategory());
        System.out.println("CD Director: " + (this.getDirector() != null ? this.getDirector() : "N/A"));
        System.out.println("Total Length: " + this.getLength() + "s");
         System.out.println("--- Tracks ---");
        boolean canPlay = false;
        for (Track track : tracks) {
             if (track.getLength() > 0) {
                track.play();
                canPlay = true;
             } else {
                 System.out.println("Skipping track: " + track.getTitle() + " - Length is zero or less.");
             }
        }
         if (!canPlay) {
              System.out.println("Warning: None of the tracks in the CD '" + this.getTitle() + "' could be played (length <= 0).");
         }
        System.out.println("------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("CD [%d] - %s - %s - %s - %s - %d: %.2f $ (Tracks: %d)",
                getId(),
                getTitle(),
                (getCategory() != null ? getCategory() : "N/A"),
                (getDirector() != null ? getDirector() : "N/A"),
                (artist != null ? artist : "N/A"),
                getLength(),
                getCost(),
                tracks.size());
    }
}