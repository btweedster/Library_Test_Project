package test;

import src.Media;
import src.Book;
import src.Patron;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestPatron {
    
    @Test 
    public void testConstr() {
        Patron p = new Patron("Bilbo Baggins");
        assertNotNull(p);
    }

    @Test 
    public void testSetGet() {
        Patron p = new Patron("Bilbo Baggins");
        assertEquals(p.getId(),"P");
        p.setId("P0000001");
        assertEquals(p.getId(),"P0000001");
        p.setName("Frodo Baggins");
        assertEquals(p.getName(),"Frodo Baggins");
    }

    @Test
    public void testCheckout() {
        Patron p = new Patron("Bilbo Baggins");
        p.setId("P0000001");
        Media m = new Book("The Hobbit","J.R.R. Tolkien");
        p.checkout(m);
        assertEquals(p.toString(),"Patron ID: P0000001\n" +
            "Name: Bilbo Baggins\n" +
            "Checked out Media:" + 
            "\n\tLibrary ID: B" + 
            "\n\tTitle: The Hobbit" +
            "\n\tDue Date: " + m.returnDate().toString() + "\n");
        assertFalse(p.checkout(m));
    }

    @Test 
    public void testCheckin() {
        Patron p = new Patron("Bilbo Baggins");
        p.setId("P0000001");
        Media m = new Book("The Hobbit","J.R.R. Tolkien");
        p.checkout(m);
        p.checkin(m);
        assertEquals(p.toString(),"Patron ID: P0000001\n" +
            "Name: Bilbo Baggins");
        assertFalse(p.checkin(m));
    }
}
