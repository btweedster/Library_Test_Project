package test;

import static org.junit.Assert.*;
import org.junit.Test;

import src.Book;

public class TestBook {
    @Test 
    public void TestConstr() {
        Book b = new Book("The Lord of the Rings");
        assertNotNull(b);
        b = new Book("The Lord of the Rings","J.R.R. Tolkien");
        assertNotNull(b);
    }

    @Test
    public void testSetGet() {
        Book b = new Book("The Lord of the Rings","Tolkien");
        assertEquals(b.getAuthor(),"Tolkien");
        b.setAuthor("J.R.R. Tolkien");
        assertEquals(b.getAuthor(),"J.R.R. Tolkien");
        assertNull(b.getISBN()); 
        b.setISBN("123-45678910");
        assertEquals(b.getISBN(),"123-45678910");
    }

    @Test
    public void testToString() {
        Book b = new Book("The Hobbit");
        assertEquals(b.toString(),"Library ID: B\nTitle: The Hobbit");
        b.setAuthor("J.R.R. Tolkien");
        assertEquals(b.toString(),"Library ID: B\nTitle: The Hobbit\nAuthor: J.R.R. Tolkien");
        b.setISBN("123-45678910");
        assertEquals(b.toString(),"Library ID: B\nTitle: The Hobbit\nAuthor: J.R.R. Tolkien\nISBN: 123-45678910");
    }

    @Test 
    public void testEqualsHash() {
        Book b1 = new Book("The Lord of the Rings");
        b1.setId("B000001");
        Book b2 = new Book("The Hobbit");
        b2.setId("B000001");
        assertTrue(b1.equals(b2));
        assertTrue(b1.hashCode() == b2.hashCode());
        b2.setId("B000002");
        assertFalse(b1.equals(b2));
        assertFalse(b1.hashCode() == b2.hashCode());
    }
}
