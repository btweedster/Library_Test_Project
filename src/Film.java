package src;

/**
 * Extends Media to hold films.
 * @version 2026-09-11
 * Film
 */
public class Film extends Media {
    private Integer year;
    private String director;

    /**
     * Primary Constructor
     * @param title Title of the film
     */
    public Film(String title) {
        super("F",title);
    }

    /**
     * Specify year at creation
     * @param title Title of the film
     * @param year Year the film was released
     */
    public Film(String title, Integer year) {
        this(title);
        this.year = year;
    }

    /**
     * Manaully set the release year, overwrites old year if it exists.
     * @param year Year the film was released.
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Get the year the film was released.
     * @return Year the film was released.
     */
    public Integer getYear() {
        return this.year;
    }

    /**
     * Set the director of the film.
     * @param director Director of the film
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Get the director of the film
     * @return director of the film.
     */
    public String getDirector() {
        return this.director;
    }

    public String toString() {
        String rString = super.toString();

        if (year != null) {
            rString += " (" + year + ")";
        }

        if (director != null) {
            rString += "\nDirector: " + director;
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