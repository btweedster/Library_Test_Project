package src;

/**
 * Extends Media to hold books.
 * @version 2026-09-11
 * Book
 */

public class Book extends Media{
    private String isbn;
    private String author;

    /**
     * Primary Constructor.
     * @param title The title of the book.
     */
    public Book(String title) {
        super("B",title);
    }

    /**
     * Specify author at creation
     * @param id Starts with "B". Must be unique within Library.
     * @param title The title of the book.
     * @param author The author of the book.
     */
    public Book(String title, String author) {
        this(title);
        this.author = author;
    }

    /**
     * Manually set the author of the book. Overwrites author if it exists.
     * @param author The author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Manually set the ISBN of the book.
     * @param isbn International Standard Book Number
     */
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public String toString() {
        String rString = super.toString() +
            "\nAuthor: " + author;
        
        if (isbn != null) {
            rString += "\n ISBN: " + isbn;
        }

        return rString;
    }
}
