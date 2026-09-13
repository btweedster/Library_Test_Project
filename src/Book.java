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
     * Specify author at creationy.
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
     * Get the author of the book
     * @return author of the book
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Manually set the ISBN of the book.
     * @param isbn International Standard Book Number
     */
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Get the ISBN of the book
     * @return ISBN
     */
    public String getISBN() {
        return this.isbn;
    }

    public String toString() {
        String rString = super.toString();

        if (author != null) {
            rString += "\nAuthor: " + author;
        }
        
        if (isbn != null) {
            rString += "\nISBN: " + isbn;
        }

        return rString;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Media && super.equals((Media)o);
    }

    @Override 
    public int hashCode() {
        return super.hashCode();
    }
    
}
