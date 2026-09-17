package src;

import java.util.Map;
import java.util.HashMap;

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
}