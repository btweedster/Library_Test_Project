package src;

import java.util.List;
import java.util.ArrayList;

/**
 * Extends Media to hold music albums.
 * @version 2026-09-11
 */

public class Music extends Media {
    private String artist;
    private List<String> tracks;

    /**
     * Primary constructor.
     * @param title The title of the music album
     */
    public Music(String title) {
        super("M", title);
        this.tracks = new ArrayList<String>();
    }
    /**
     * Specify artist at creation.
     * @param title The title of the music album
     * @param artist The artist of the music album
     */
    public Music(String title, String artist) {
        this(title);
        this.artist = artist;
    }
    /**
     * Manually set the artist for the album
     * @param artist The artist of the music album
     */
    public void set_artist(String artist) {
        this.artist = artist;
    }

    /**
     * Get the music album artist
     * @return artist
     */
    public String get_artist() {
        return artist;
    }

    /**
     * Sets the tracks for the album. Overwrites any track list if it exists.
     * @param tracks list of tracks
     */
    public void set_tracks(List<String> tracks) {
        this.tracks = tracks;
    }

    /**
     * Add a single track to the list of tracks for the album.
     * @param track The name of the track to append to the list of tracks.
     * @return true if successfully added, otherwise false.
     */
    public boolean add_track(String track) {
        if (tracks.contains(track)) {
            return false;
        }
        tracks.add(track);
        return true;
    }

    /**
     * Returns the list of tracks in the album.
     * @return List of tracks.
     */
    public List<String> get_tracks() {
        return tracks;
    }

    /**
     * Return the name of the specified number track.
     * @param track The track number whose name will be returned
     * @return Name of the specified track
     */
    public String get_track(int track_num) {
        return tracks.get(track_num);
    }

    public String toString() {
        String rString = super.toString() +
            "\nArtist: " + artist;
        
        if (!tracks.isEmpty()) {
            rString += "Tracks: ";
            for (int i = 0; i < tracks.size(); i++){
                rString += "\n\t" + (i+1) + ". " + tracks.get(i);
            }
        }
        return rString;
    }
}
