package src;

import java.util.Map;
import java.util.HashMap;

/**
 * Used to simultate a connection to an external data source.
 */
public class DataConnect {
    Map<Character,Map<String,Media>> contents;
    Integer bookCount = 0;
    Integer musicCount = 0;
    Integer filmCount = 0;

    public DataConnect() {
        contents = new HashMap<Character,Map<String,Media>>();
        contents.put('B',new HashMap<String,Media>());
        contents.put('M',new HashMap<String,Media>());
        contents.put('F',new HashMap<String,Media>());
    }

    public boolean addMedia(Media m) {
        if (contents.get(m.getId().charAt(0)).containsKey(m.getId())) {
            return false;
        }
        contents.get(m.getId().charAt(0)).put(m.getId(),m);
        return true;
    }
}