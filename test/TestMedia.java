package test;

import src.Media;
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
    }

    @Test 
    public void testReturnData() {
        Media m = new Media("M","Test Media");
        LocalDate dueDate = m.returnDate();
        assertEquals(dueDate.toString(),"2026-09-27");
    }
}
