package src;

import java.util.Map;
import java.util.HashMap;

/**
 * Used to simultate a connection to an external data source for testing purposes.
 */
public class DataConnect {
    Map<Character,Map<String,Media>> contents;
    Map<String,Patron> patrons;
    // Integer bookCount = 0;
    // Integer musicCount = 0;
    // Integer filmCount = 0;
    // Integer patronCount = 0;

    public DataConnect() {
        patrons = new HashMap<String,Patron>();
        contents = new HashMap<Character,Map<String,Media>>();
        contents.put('B',new HashMap<String,Media>());
        contents.put('M',new HashMap<String,Media>());
        contents.put('F',new HashMap<String,Media>());
    }

    public void addMedia(Media m) {
        Map<String,Media> mediaMap = contents.get(m.getId().charAt(0));
        String newID = m.getId() + String.format("%07d",mediaMap.size() + 1);
        m.setId(newID);
        mediaMap.put(m.getId(),m);
    }

    public Media getMedia(String id) {
        return contents.get(id.charAt(0)).get(id);
    } 

    public boolean addPatron(Patron p) {
        if (!patrons.containsKey(p.getId())) {
            patrons.put(p.getId(),p);
            return true;
        }
        return false;
    }

    public Patron getPatron(String id) {
        return patrons.get(id);
    }
}