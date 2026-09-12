package test;

import src.Author;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestAuthor {

    @Test
    public void testToString() {
        Author author = new Author("J.R.R.","Tolkien");
        assertEquals(author.toString(),"Tolkien, J.R.R.");
    }
}
