package test;

import src.Media;
import src.Book;
import java.time.LocalDate;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestMedia {
    @Test
    public void testConstr() {
        Media m = new Media("M","Test Media");
        assertNotNull(m);
    }

    @Test
    public void testSetGet() {
        Media m = new Media("M","Test Media");
        assertEquals(m.getId(),"M");
        m.setId("M0000001");
        assertEquals(m.getId(),"M0000001");
        assertEquals(m.getTitle(),"Test Media");
    }

    @Test
    public void testToString() {
        Media m = new Media("M0000001","Test Media");
        assertEquals(m.toString(),"Library ID: M0000001\nTitle: Test Media");
        Media b = new Book("The Hobbit","J.R.R. Tolkien");
        assertEquals(b.toString(),"Library ID: B\nTitle: The Hobbit\nAuthor: J.R.R. Tolkien");
        ((Book)b).setISBN("123-45678910");
        assertEquals(b.toString(),"Library ID: B\nTitle: The Hobbit\nAuthor: J.R.R. Tolkien\nISBN: 123-45678910");
    }

    @Test 
    public void testReturnData() {
        Media m = new Media("M","Test Media");
        LocalDate dueDate = m.returnDate();
        assertEquals(dueDate.toString(),"2026-09-27");
    }
}
