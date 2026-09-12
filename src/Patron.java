package src;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;

/**
 * Data class that holds patrons of the library and what they have checked out.
 * Patron
 * @version 2026-09-12
 */
public class Patron {
    private String id;
    private String name;
    private Map<Media,LocalDate> checkouts;

    /**
     * Primary constructor class
     * @param name
     */
    public Patron(String name) {
        this.id = "P";
        this.name = name;
        checkouts = new HashMap<Media,LocalDate>();
    }

    /**
     * Sets the Patron's unqiue ID.
     * @param id unique id of patron
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * returns the patron's unique ID
     * @return
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the patron's name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the patron's name
     * @return the patron's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks out the media item under this patron
     * @param m the Media item to be checked out
     * @return true if transaction successful, otherwise false.
     */
    public boolean checkout(Media m) {
        if (!checkouts.containsKey(m)) {
            checkouts.put(m,m.returnDate());
            return true;
        }
        return false;
    }

    /**
     * Check in the Media item 
     * @param m the Media item to be checking in
     * @return true if transactions successful, otherwise false
     */
    public boolean checkin(Media m) {
        if (checkouts.containsKey(m)) {
            checkouts.remove(m);
            return true;
        }
        return false;
    }
}