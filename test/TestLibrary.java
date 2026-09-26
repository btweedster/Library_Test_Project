package test;

import src.Library;
import src.Media;
import src.Patron;
import java.time.LocalDate;
import java.util.List;

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
        assertEquals(l.getPatron("P0000001").getName(),"Bilbo Baggins");

        // test with test data
        l = new Library(true);
        l.addPatron("Bob Ross");
        assertEquals(l.getPatron("P0000001").getName(),"Bob Ross");
        assertEquals(l.getPatron("P0000008").getName(),"Bob Ross");
    }

    @Test
    public void testRemovePatron() {
        // Remove existing Patron
        Library l = new Library(true);
        assertTrue(l.removePatron("P0000001"));
        assertNull(l.getPatron("P0000001"));
        
        // remove added Patron
        l.addPatron("Bilbo Baggins");
        assertNotNull(l.getPatron("P0000008"));
        assertTrue(l.removePatron("P0000008"));
        assertNull(l.getPatron("P0000008"));
    }

    @Test 
    public void testAddMedia() {
        // test with blank library
        Library l = new Library();
        // New Book
        assertTrue(l.addMedia("Neuromancer", 'B'));
        assertEquals(l.getMedia("B0000001").getTitle(),"Neuromancer");
        // New Music
        assertTrue(l.addMedia("Van Cliburn: Beethoven Sonatas",'M'));
        assertEquals(l.getMedia("M0000001").getTitle(),"Van Cliburn: Beethoven Sonatas");
        // New Films
        assertTrue(l.addMedia("Guardians of the Galaxy",'F'));
        assertEquals(l.getMedia("F0000001").getTitle(),"Guardians of the Galaxy");
        
        // test with test data
        l = new Library(true);
        // New Book
        assertTrue(l.addMedia("Neuromancer", 'B'));
        assertEquals(l.getMedia("B0000009").getTitle(),"Neuromancer");
        // New Music
        assertTrue(l.addMedia("Van Cliburn: Beethoven Sonatas",'M'));
        assertEquals(l.getMedia("M0000004").getTitle(),"Van Cliburn: Beethoven Sonatas");
        // New Films
        assertTrue(l.addMedia("Guardians of the Galaxy",'F'));
        assertEquals(l.getMedia("F0000009").getTitle(),"Guardians of the Galaxy");
    }

    @Test
    public void testRemoveMedia() {
        // Remove existing
        Library l = new Library(true);
        // Remove Book
        assertTrue(l.removeMedia("B0000001"));
        assertNull(l.getMedia("B0000001"));
        assertFalse(l.removeMedia("B0000001"));
        // Remove Music
        assertTrue(l.removeMedia("M0000001"));
        assertNull(l.getMedia("M0000001"));
        assertFalse(l.removeMedia("M0000001"));
        // Remove Film
        assertTrue(l.removeMedia("F0000001"));
        assertNull(l.getMedia("F0000001"));
        assertFalse(l.removeMedia("F0000001"));

        // remove added
        // Remove Book
        assertTrue(l.addMedia("Dune", 'B'));
        assertNotNull(l.getMedia("B0000009"));
        assertTrue(l.removeMedia("B0000009"));
        assertNull(l.getMedia("B0000009"));
        assertFalse(l.removeMedia("B0000009"));
        // Remove Music
        assertTrue(l.addMedia("Random Access Memories", 'M'));
        assertNotNull(l.getMedia("M0000004"));
        assertTrue(l.removeMedia("M0000004"));
        assertNull(l.getMedia("M0000004"));
        assertFalse(l.removeMedia("M0000004"));
        // Remove Film
        assertTrue(l.addMedia("Hook", 'F'));
        assertNotNull(l.getMedia("F0000009"));
        assertTrue(l.removeMedia("F0000009"));
        assertNull(l.getMedia("F0000009"));
        assertFalse(l.removeMedia("F0000009"));
    }

    @Test 
    public void testFindPatron() {
        Library l = new Library(true);
        // Find existing patron
        assertEquals(l.findPatron("Bob Ross").size(),1);
        // Find added patron
        assertTrue(l.findPatron("Jean-Luc Picard").isEmpty());
        l.addPatron("Jean-Luc Picard");
        assertEquals(l.findPatron("Jean-Luc Picard").size(),1);
        // Find patrons with the same name
        l.addPatron("Dolly Parton");
        List<Patron> pList = l.findPatron("Dolly Parton");
        assertEquals(pList.size(),2);
        assertTrue(pList.contains(l.getPatron("P0000003")));
        assertTrue(pList.contains(l.getPatron("P0000009")));
    }

    @Test 
    public void testFindMedia() {
        Library l = new Library(true);
        // Find existing media
        // Book
        assertEquals(l.findMedia("The Hobbit").size(),1);
        // Music
        assertEquals(l.findMedia("A Hard Day's Night").size(),1);
        // Film
        assertEquals(l.findMedia("Star Wars").size(),1);

        // Find added media
        assertTrue(l.addMedia("The Hobbit",'M'));
        assertTrue(l.addMedia("The Hobbit",'F'));
        List<Media> mList = l.findMedia("The Hobbit");
        assertEquals(mList.size(),3);
        assertTrue(mList.contains(l.getMedia("B0000001")));
        assertTrue(mList.contains(l.getMedia("M0000004")));
        assertTrue(mList.contains(l.getMedia("F0000009")));
    }

    @Test 
    public void testCheckOutIn() {
        Library l = new Library(true);
        Patron p = l.getPatron("P0000001");
        Media m = l.getMedia("B0000001");
        assertEquals(m.getStatus(),"Status: Available");

        // Use today's date
        LocalDate today = LocalDate.now();
        assertFalse(l.checkin(m.getId()));
        assertTrue(l.checkout(p.getId(), m.getId())); //Uses today by default
        assertFalse(l.checkout(p.getId(),m.getId()));
        assertEquals(m.getStatus(),"Status: Checked out\nPatron: Bob Ross\nDue Date: " + today.plusDays(30));
        assertTrue(l.checkin(m.getId()));
        assertEquals(m.getStatus(),"Status: Available");
        assertFalse(l.checkin(m.getId()));

        // Specify date
        LocalDate d = LocalDate.of(2026,9,1);
        assertFalse(l.checkin(m.getId()));
        assertTrue(l.checkout(p.getId(), m.getId(),d)); // Uses specified day
        assertFalse(l.checkout(p.getId(),m.getId(),d));
        assertEquals(m.getStatus(),"Status: Checked out\nPatron: Bob Ross\nDue Date: " + d.plusDays(30));
        assertTrue(l.checkin(m.getId()));
        assertEquals(m.getStatus(),"Status: Available");
        assertFalse(l.checkin(m.getId()));
    }
    
}


