package test;

import src.Film;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestFilm {
    
    @Test
    public void TestContr() {
        Film f = new Film("Star Wars");
        assertNull(f.getDirector());
    }
}
