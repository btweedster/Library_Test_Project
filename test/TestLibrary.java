package test;

import src.Library;
import src.Media;
import src.Book;
import src.Patron;
import java.time.LocalDate;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestLibrary {

    @Test
    public void testConstr() {
        Library l = new Library();
        assertNotNull(l);
        l = new Library(true);
        assertNotNull(l);
    }

    @Test 
    public void testAddPatron() {
        // test with blank library
        Library l = new Library();
        l.addPatron("Bilbo Baggins");

        // test with loaded library
    }

    @Test
    public void testRemovePatron() {
        // Remove existing
        // remove added
    }

    @Test 
    public void testAddMedia() {

    }

    @Test
    public void testRemoveMedia() {
        // Remove existing
        // remove added
    }

    @Test 
    public void testFindPatron() {
        // Find existing patron
        // Find added patron
    }

    @Test 
    public void testFindMedia() {
        // Find existing media
        // find added media
    }
    
}


