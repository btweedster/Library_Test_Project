package test;

import src.Music;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestMusic {

    @Test 
    public void TestConstr() {
        Music m = new Music("A Hard Day's Night");
        assertNotNull(m);
        m = new Music("A Hard Day's Night","The Beatles");
        assertNotNull(m);
    }

    @Test
    public void testSetGet() {
        Music m = new Music("A Hard Day's Night","Harrison");
        assertEquals(m.getArtist(),"Harrison");
        m.setArtist("The Beatles");
        assertEquals(m.getArtist(),"The Beatles");
        m.addTrack("Two of Us");
        assertEquals(m.getTrack(1),"Two of Us");
        assertEquals(m.getTracks().size(),1);
        m.addTrack("Dig A Pony");
        assertEquals(m.getTrack(2), "Dig A Pony");
        assertEquals(m.getTracks().size(),2);
        m.setTracks(new ArrayList<>(List.of("Two Of Us","Dig A Pony","Across the Universe")));
        assertEquals(m.getTrack(3),"Across the Universe");
        assertEquals(m.getTracks().size(),3);
    }

    @Test
    public void testToString() {
        Music m = new Music("Let It Be");
        assertEquals(m.toString(),"Library ID: M\nTitle: Let It Be");
        m.setArtist("The Beatles");
        assertEquals(m.toString(),"Library ID: M\nTitle: Let It Be\nArtist: The Beatles");
        m.setTracks(new ArrayList<>(List.of("Two Of Us","Dig A Pony","Across the Universe")));
        String testString = "Library ID: M\nTitle: Let It Be\nArtist: The Beatles" +
            "\nTracks:" +
            "\n\t1. Two Of Us" +
            "\n\t2. Dig A Pony" +
            "\n\t3. Across the Universe";
        assertEquals(m.toString(),testString);
    }

    @Test 
    public void testEqualsHash() {
        Music m1 = new Music("A Hard Day's Night");
        m1.setId("B000001");
        Music m2 = new Music("The Hobbit");
        m2.setId("B000001");
        assertTrue(m1.equals(m2));
        assertTrue(m1.hashCode() == m2.hashCode());
        m2.setId("B000002");
        assertFalse(m1.equals(m2));
        assertFalse(m1.hashCode() == m2.hashCode());
    }
}