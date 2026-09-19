package src;

import java.util.Map;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Used to simultate a connection to an external data source for testing purposes.
 */
public class DataConnect {
    Map<Character,Map<String,Media>> contents;
    Map<String,Patron> patrons;
    Integer bookCount = 0;
    Integer musicCount = 0;
    Integer filmCount = 0;
    Integer patronCount = 0;

    /**
     * Sets up HashMaps to hold the patrons and contents of the library to simulate a RDBMS
     */
    public DataConnect() {
        patrons = new HashMap<String,Patron>();
        contents = new HashMap<Character,Map<String,Media>>();
        contents.put('B',new HashMap<String,Media>());
        contents.put('M',new HashMap<String,Media>());
        contents.put('F',new HashMap<String,Media>());
    }

    /**
     * Adds a media item to the contents. ID is automatically assigned
     * @param m The media to be added
     */
    public void addMedia(Media m) {
        char charKey = m.getId().charAt(0);
        Integer count;

        if (charKey == 'B') {
            count = bookCount;
        } else if (charKey == 'M') {
            count = musicCount;
        } else {
            count = filmCount;
        }

        Map<String,Media> mediaMap = contents.get(charKey);
        String newID = charKey + String.format("%07d",count + 1);
        m.setId(newID);
        mediaMap.put(m.getId(),m);
        
        if (charKey == 'B') {
            bookCount++;
        } else if (charKey == 'M') {
            musicCount++;
        } else {
            filmCount++;
        }
    }

    /**
     * Removes a media item from the contents
     * @param id the ID of the media item to be removed
     * @return true if media is successfully removed, otherwise false
     */
    public boolean removeMedia(String id) {
        char charKey = id.charAt(0);
        Map<String,Media> mediaMap = contents.get(charKey);
        if (mediaMap.containsKey(id)) {
            mediaMap.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Get a media item from the library contents
     * @param id the ID of media item 
     * @return the Media object
     */
    public Media getMedia(String id) {
        // Character c = id.charAt(0);
        // Map<String,Media> m = contents.get(id.charAt(0));
        // return m.get(id);
        return contents.get(id.charAt(0)).get(id);
    }

    /**
     * Add a patron to the library
     * @param p The name of the patron
     */
    public void addPatron(Patron p) {
        String newID = "P" + String.format("%07d",patronCount + 1);
        p.setId(newID);
        patrons.put(p.getId(),p);
        patronCount += 1;
    }

    /**
     * Removes a patron from the library if they exist
     * @param id The id of the patron to be removed
     * @return true if the patron was successfully removed, otherwise false
     */
    public boolean removePatron(String id) {
        if (patrons.containsKey(id)) {
            patrons.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Get a patron from the library
     * @param id The id of the patron
     * @return the Patron object
     */
    public Patron getPatron(String id) {
        return patrons.get(id);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Loads csv data from test/TestDatabase
     */
    public void loadTestData() {
        String csvLine;

        // Load Patrons
        try {
            BufferedReader b = new BufferedReader(new FileReader("test/TestDatabase/Patrons.csv"));
            while ((csvLine = b.readLine()) != null) {
                String[] data = csvLine.split(",");
                Patron p = new Patron(data[1]);
                this.addPatron(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load Books
        try {
            BufferedReader b = new BufferedReader(new FileReader("test/TestDatabase/Books.csv"));
            while ((csvLine = b.readLine()) != null) {
                String[] data = csvLine.split(",");
                Book book = new Book(data[1],data[2]);
                book.setISBN(data[3]);
                this.addMedia(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load Films
        try {
            BufferedReader b = new BufferedReader(new FileReader("test/TestDatabase/Films.csv"));
            while ((csvLine = b.readLine()) != null) {
                String[] data = csvLine.split(",");
                Film film = new Film(data[1],Integer.valueOf(data[2]));
                film.setDirector(data[3]);
                this.addMedia(film);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load Music
        try {
            BufferedReader b = new BufferedReader(new FileReader("test/TestDatabase/Music.csv"));
            while ((csvLine = b.readLine()) != null) {
                String[] data = csvLine.split(",");
                Music music = new Music(data[1],data[2]);
                this.addMedia(music);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load Music Tracks
        try {
            BufferedReader b = new BufferedReader(new FileReader("test/TestDatabase/Tracks.csv"));
            while ((csvLine = b.readLine()) != null) {
                String[] data = csvLine.split(",");
                String musicID = data[0].replace("\uFEFF", "");
                String track = data[1];
                Music m = (Music) this.getMedia(musicID);
                m.addTrack(track); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}