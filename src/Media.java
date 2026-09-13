package src;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Parent data class that represents items within the library.
 * @version 2026-09-11
 * Media
 */

public class Media {
    private String id;
    private String title;
    private LocalDate dueDate;
    private Patron patron;

    /**
     * Primary Constructor
     * @param id Unique ID of the media item
     * @param title Title of the media item.
     */
    public Media(String id,String title) {
        this.id = id;
        this.title = title;
        this.dueDate = null;
    }
    
    /**
     * Set the Unique ID 
     * @param id unique ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the ID of the media item
     * @return the unqiue ID of the media item.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the title of the media item
     * @return the title of the media item.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gives the due date of a Media item based on the type of Media itme.
     * @return the due date of the item.
     */
    public LocalDate returnDate() {
        int checkoutDays = 0;
        if (id.charAt(0) == 'B') {
            checkoutDays = 30;
        } else if (id.charAt(0) == 'M') {
            checkoutDays = 14;
        } else {
            checkoutDays = 7;
        }
        return LocalDate.now().plusDays(checkoutDays);
    }

    /**
     * Checks this Media item out under the name of the patron that is supplied
     * @param p The patron checking out this item
     * @return true if the transaction is successful, otherwise false.
     */
    public boolean checkout(Patron p) {
        if (dueDate == null && p.checkout(this)) {
            this.patron = p;
            this.dueDate = returnDate();
            return true;
        }
        return false;
    }

    /**
     * Checks the Media item in to the library
     * @return true if the transactions is successful, other false.
     */
    public boolean checkin() {
        if (dueDate != null && this.patron.checkin(this)) {
            this.patron = null;
            this.dueDate = null;
            return true;
        }
        return false;
    }

    public String toString() {
        return "Library ID: " + id + 
            "\nTitle: " + title;
    }

    /**
     * Gets the status of the item. If checked out return's patron's name and the due date.
     * @return Media item status
     */
    public String getStatus() {
        String rString = "Status: ";
        if (dueDate != null) {
            rString += "Checked out" +
                "\nPatron: " + patron.getName() +
                "\nDue Date: " + dueDate.toString();
        } else {
            rString += "Available";
        }
        return rString;
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Media m = (Media) o;
        return this.id == m.getId();
    }

    @Override 
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
