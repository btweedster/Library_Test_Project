package src;

import java.util.List;
import java.time.LocalDate;

/**
 * Thee main controller class for this library system. And interface should only need to connect to this.
 * Library
 */
public class Library {
    DataConnect dc;

    /**
     * Primary constructor
     */
    public Library() {
        dc = new DataConnect();
    } 

    /**
     * Test Constructor to load test data
     * @param test set to true to load test data on contruction
     */
    public Library(boolean test) {
        this();
        if (test) {
            dc.loadTestData();
        }
    }

    /**
     * add a Patron to the library
     * @param name the name of the patron
     */
    public void addPatron(String name) {
        dc.addPatron(new Patron(name));
    }

    /**
     * remove a patron from the library
     * @param id the ID of the patron to be removed
     * @return true if the patron was successfully removed, otherwise false
     */
    public boolean removePatron(String id) {
        return dc.removePatron(id);
    }

    /**
     * Return a patron based on the patron id
     * @param id The ID of the patron
     * @return patron object
     */
    public Patron getPatron(String id) {
        return dc.getPatron(id);
    }

    /**
     * Return a list of the patron whose names match the input parameter
     * @param name the name to search for, must be an exact match
     * @return a List of patrons matching the name, if any
     */
    public List<Patron> findPatron(String name) {
        return dc.findPatron(name);
    }

    /**
     * Add a media item to the library
     * @param title The title of the media 
     * @param type The type of the media 'B' = Book, 'M' = Music, 'F' = Film
     * @return true if the media item was successfully added, otherwise false
     */
    public boolean addMedia(String title, char type) {
        Media m = null;
        switch (type) {
            case 'B' : 
                m = new Book(title);
                break;
            case 'M' :
                m = new Music(title);
                break;
            case 'F' :
                m = new Film(title);
                break;
        }
        if (m == null) {
            return false;
        }
        dc.addMedia(m);
        return true;
    }

    /**
     * Add a media item to the Library, overloaded to add secondary Media fields
     * @param title The title of the media
     * @param secParam Secondary Parameter, Book = Author, Music = Artist, Film = Year
     * @param type The type of the media 'B' = Book, 'M' = Music, 'F' = Film
     * @return true if the media was successfully added, otherwise false
     */
    public boolean addMedia(String title, String secParam, char type) {
        Media m = null;
        switch (type) {
            case 'B' : 
                m = new Book(title,secParam);
                break;
            case 'M' :
                m = new Music(title,secParam);
                break;
            case 'F' :
                m = new Film(title,Integer.valueOf(secParam));
                break;
        }
        if (m == null) {
            return false;
        }
        dc.addMedia(m);
        return true;
    }

    /**
     * Removes a media item from the library
     * @param id The ID of the Media item
     * @return true if item was successfully removed, otherwise false
     */
    public boolean removeMedia(String id) {
        return dc.removeMedia(id);
    }

    /**
     * Return the media item that matches the id
     * @param id the ID of the media to get
     * @return Media object
     */
    public Media getMedia(String id) {
        return dc.getMedia(id);
    }

    /**
     * Get a list of media items that match the title
     * @param title The title to search, mist exactly match to be returned
     * @return List of Media object whose titles match
     */
    public List<Media> findMedia(String title) {
        return dc.findMedia(title);
    }

    /**
     * Checks out a media item under a patron, default uses today as checkout date
     * @param pID The ID of the patron
     * @param mID The ID of the media item
     * @return true if the item was successfully checked out, otherwise false
     */
    public boolean checkout(String pID, String mID) {
        return checkout(pID,mID,LocalDate.now());
    }

    /**
     * Checks out media item under a patron
     * @param pID The ID of the patron
     * @param mID The ID of the media item
     * @param d The date of checkout
     * @return true if the item was successfully checked out, otherwise false
     */
    public boolean checkout(String pID, String mID, LocalDate d) {
        Patron p = dc.getPatron(pID);
        Media m = dc.getMedia(mID);
        if (p != null && m != null) {
            return dc.getMedia(mID).checkout(dc.getPatron(pID),d);
        }
        return false;
    }

    /**
     * Checks a media item in
     * @param mID The media item to be checked in
     * @return true if the item was successfully checked in, otherwise false
     */
    public boolean checkin(String mID) {
        Media m = dc.getMedia(mID);
        if (m != null) {
            return dc.getMedia(mID).checkin();
        }
        return false;
    }
}
