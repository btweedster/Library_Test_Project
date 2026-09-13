package test;

import src.Film;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestFilm {
    
    @Test
    public void TestContr() {
        Film f = new Film("Star Wars");
        assertNotNull(f);
        f = new Film("Star Wars",1977);
        assertNotNull(f);
    }
    @Test
    public void testSetGet() {
        Film f = new Film("Star Wars",1967);
        assertTrue(f.getYear() == 1967);
        f.setYear(1977);
        assertTrue(f.getYear() == 1977);
        assertNull(f.getDirector());
        f.setDirector("George Lucas");
        assertEquals(f.getDirector(),"George Lucas");
    }

    @Test
    public void testToString() {
        Film f = new Film("Star Wars");
        assertEquals(f.toString(),"Library ID: F\nTitle: Star Wars");
        f.setYear(1977);
        assertEquals(f.toString(),"Library ID: F\nTitle: Star Wars (1977)");
        f.setDirector("George Lucas");
        assertEquals(f.toString(),"Library ID: F\nTitle: Star Wars (1977)\nDirector: George Lucas");
    }

    @Test 
    public void testEqualsHash() {
        Film f1 = new Film("Star Wars");
        f1.setId("F000001");
        Film f2 = new Film("Star Wars");
        f2.setId("F000001");
        assertTrue(f1.equals(f2));
        assertTrue(f1.hashCode() == f2.hashCode());
        f2.setId("F000002");
        assertFalse(f1.equals(f2));
        assertFalse(f1.hashCode() == f2.hashCode());
    }
}
