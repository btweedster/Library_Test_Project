package src;

/**
 * Parent data class that represents items within the library.
 * @version 2026-09-11
 * Media
 */

public class Media {
    private String id;
    private String title;

    /**
     * Primary Constructor
     * @param id Unique ID of the media item
     * @param title Title of the media item.
     */
    public Media(String id,String title) {
        this.id = id;
        this.title = title;
    }
    
    /**
     * Set the Unique ID 
     * @param id unique ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return the unqiue ID of the media item.
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @return the title of the media item.
     */
    public String getTitle() {
        return title;
    }

    public String toString() {
        return "Library ID: " + id + 
            "\nTitle: " + title;
    }
}
