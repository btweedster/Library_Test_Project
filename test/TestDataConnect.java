package test;

import src.Media;
import src.Book;
import src.DataConnect;
import src.Music;
import src.Film;
import src.Patron;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestDataConnect {
    
    @Test 
    public void testConstr() {
        DataConnect dc = new DataConnect();
        assertNotNull(dc);
    }

    @Test 
    public void testAddRemovePatron() {
        DataConnect dc = new DataConnect();
        Patron p = new Patron("Bilbo Baggins");
        dc.addPatron(p);
        String testPatronID = "P0000001";
        assertEquals(p.getId(),testPatronID);
        assertEquals(dc.getPatron(testPatronID).getName(),"Bilbo Baggins");

        Patron p2 = new Patron("Frodo Baggins");
        dc.addPatron(p2);
        assertEquals(dc.getPatron("P0000002").getName(),"Frodo Baggins");
        assertTrue(dc.removePatron(p2.getId()));

        Patron p3 = new Patron("Samwise Gamgee");
        dc.addPatron(p3);
        assertEquals(dc.getPatron("P0000003").getName(),"Samwise Gamgee");
    }

    @Test 
    public void testAddMedia() {
        Media b1 = new Book("The Hobbit","J.R.R. Tolkien");
        Media b2 = new Book("The Lord of the Rings","J.R.R. Tolkien");
        Media b3 = new Book("The Silmarillion","J.R.R. Tolkien");
        Media m1 = new Music("A Hard Day's Night","The Beatles");
        Media m2 = new Music("Revolver","The Beatles");
        Media m3 = new Music("Let It Be","The Beatles");
        Media f1 = new Film("Star Wars",1977);
        Media f2 = new Film("The Empire Strikes Back",1980);
        Media f3 = new Film("Return of the Jedi",1983);

        DataConnect dc = new DataConnect();

        dc.addMedia(b1);
        assertEquals(b1.getId(),"B0000001");
        assertEquals(dc.getMedia("B0000001").getTitle(),"The Hobbit");
        dc.addMedia(b2);
        assertEquals(b2.getId(),"B0000002");
        assertEquals(dc.getMedia("B0000002").getTitle(),"The Lord of the Rings");
        assertTrue(dc.removeMedia(b2.getId()));
        dc.addMedia(b3);
        assertEquals(b3.getId(),"B0000003");
        assertEquals(dc.getMedia("B0000003").getTitle(),"The Silmarillion");

        dc.addMedia(m1);
        assertEquals(m1.getId(),"M0000001");
        assertEquals(dc.getMedia("M0000001").getTitle(),"A Hard Day's Night");
        dc.addMedia(m2);
        assertEquals(m2.getId(),"M0000002");
        assertEquals(dc.getMedia("M0000002").getTitle(),"Revolver");
        assertTrue(dc.removeMedia(m2.getId()));
        dc.addMedia(m3);
        assertEquals(m3.getId(),"M0000003");
        assertEquals(dc.getMedia("M0000003").getTitle(),"Let It Be");

        dc.addMedia(f1);
        assertEquals(f1.getId(),"F0000001");
        assertEquals(dc.getMedia("F0000001").getTitle(),"Star Wars");
        dc.addMedia(f2);
        assertEquals(f2.getId(),"F0000002");
        assertEquals(dc.getMedia("F0000002").getTitle(),"The Empire Strikes Back");
        assertTrue(dc.removeMedia(f2.getId()));
        dc.addMedia(f3);
        assertEquals(f3.getId(),"F0000003");
        assertEquals(dc.getMedia("F0000003").getTitle(),"Return of the Jedi");
    }

    @Test 
    public void testFindMedia() {
        DataConnect dc = new DataConnect();

        // Find Book
        Book b = new Book("The Road");
        assertTrue(dc.findMedia("The Road").isEmpty());
        dc.addMedia(b);
        assertEquals(dc.findMedia("The Road").get(0),b);

        // Find Music
        Music m = new Music("Who Are You");
        assertTrue(dc.findMedia("Who Are You").isEmpty());
        dc.addMedia(m);
        assertEquals(dc.findMedia("Who Are You").get(0),m);
        
        // Find Film
        Film f = new Film("The Matrix");
        assertTrue(dc.findMedia("The Matrix").isEmpty());
        dc.addMedia(f);
        assertEquals(dc.findMedia("The Matrix").get(0),f);

        // Test loaded media
        dc.loadTestData();
        assertEquals(dc.findMedia("The Fellowship of the Ring").size(),1);
        assertEquals(dc.findMedia("Abbey Road").size(),1);
        assertEquals(dc.findMedia("Star Wars").size(),1);
        
        // add media with duplicate titles
        dc.addMedia(new Film("The Fellowship of the Ring"));
        assertEquals(dc.findMedia("The Fellowship of the Ring").size(),2);
        dc.addMedia(new Book("Abbey Road"));
        assertEquals(dc.findMedia("Abbey Road").size(),2);
        dc.addMedia(new Music("Star Wars"));
        assertEquals(dc.findMedia("Star Wars").size(),2);
    }

    @Test
    public void testFindPatron() {
        DataConnect dc = new DataConnect();
        Patron p = new Patron("Princess Donut");
        assertTrue(dc.findPatron("Princess Donut").isEmpty());
        dc.addPatron(p);
        assertEquals(dc.findPatron("Princess Donut").size(),1);

        // Test loaded patrons
        dc.loadTestData();
        assertEquals(dc.findPatron("Fred Rogers").size(),1);

        // Add patrons with duplicate names
        dc.addPatron(new Patron("Fred Rogers"));
        assertEquals(dc.findPatron("Fred Rogers").size(),2);
    }

    @Test 
    public void testLoadTestData() {
        DataConnect dc = new DataConnect();
        dc.loadTestData();

        // Test that each expected patron is not null
        for (int i = 1; i <= 7; i++) {
            String pID = "P000000" + i;
            assertNotNull(dc.getPatron(pID));
        }

        // Test that each expected Book is not null
        for (int i = 1; i <= 8; i++) {
            String mID = "B000000" + i;
            assertNotNull(dc.getMedia(mID));
        }

        // Test that each expected Film is not null
        for (int i = 1; i <= 8; i++) {
            String mID = "F000000" + i;
            assertNotNull(dc.getMedia(mID));
        }

        // Test that each expected Music is not null
        for (int i = 1; i <= 3; i++) {
            String mID = "M000000" + i;
            assertNotNull(dc.getMedia(mID));
        }

    }
}
