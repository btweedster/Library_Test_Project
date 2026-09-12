package test;

import src.Music;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestMusic {

    @Test
    public void testConstr() {
        Music m = new Music("M0001","A Hard Day's Night");
        assertNotNull(m);
    }

    @Test
    public void testToString() {
        Music m = new Music("M0001","A Hard Day's Night","The Beatles");
        assertEquals("Library ID: M0001\nTitle: A Hard Day's Night\nArtist: The Beatles",m.toString());
    }

    @Test
    public void test
}