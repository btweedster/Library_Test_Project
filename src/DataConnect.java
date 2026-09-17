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

    public DataConnect() {
        patrons = new HashMap<String,Patron>();
        contents = new HashMap<Character,Map<String,Media>>();
        contents.put('B',new HashMap<String,Media>());
        contents.put('M',new HashMap<String,Media>());
        contents.put('F',new HashMap<String,Media>());
    }

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

    public boolean removeMedia(String id) {
        char charKey = id.charAt(0);
        Map<String,Media> mediaMap = contents.get(charKey);
        if (mediaMap.containsKey(id)) {
            mediaMap.remove(id);
            return true;
        }
        return false;
    }

    public Media getMedia(String id) {
        return contents.get(id.charAt(0)).get(id);
    }

    public void addPatron(Patron p) {
        String newID = "P" + String.format("%07d",patronCount + 1);
        p.setId(newID);
        patrons.put(p.getId(),p);
        patronCount += 1;
    }

    public boolean removePatron(String id) {
        if (patrons.containsKey(id)) {
            patrons.remove(id);
            return true;
        }
        return false;
    }

    public Patron getPatron(String id) {
        return patrons.get(id);
    }
}