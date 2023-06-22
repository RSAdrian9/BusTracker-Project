package org.ARuiz.Model.Domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StopTest {

    @Test
    void getName() {
    }

    @Test
    void setName() {
        Stop stop = new Stop();
        stop.setName("New Stop");
        assertEquals("New Stop", stop.getName());
    }

    @Test
    void getLineas() {
    }

    @Test
    void setLineas() {
        Stop stop = new Stop();
        Line line1 = new Line(1, "Line 1", 10);
        Line line2 = new Line(2, "Line 2", 20);
        Line line3 = new Line(3, "Line 3", 30);

        stop.setLineas(List.of(line1, line2, line3));

        assertEquals(3, stop.getLineas().size());
        assertTrue(stop.getLineas().contains(line1));
        assertTrue(stop.getLineas().contains(line2));
        assertTrue(stop.getLineas().contains(line3));
    }

    @Test
    void testToString() {
        Stop stop = new Stop(1,"Test Stop");
        String expected = "Stops -> " + 1 + " " + "Test Stop";
        assertEquals(expected, stop.toString());
    }

    @Test
    void testGetName() {
        Stop stop = new Stop("Test Stop");
        assertEquals("Test Stop", stop.getName());
    }

    @Test
    void testSetName() {
        Stop stop = new Stop();
        stop.setName("New Stop");
        assertEquals("New Stop", stop.getName());
    }

    @Test
    void testGetLineas() {
        Stop stop = new Stop();
        List<Line> lines = new ArrayList<>();
        lines.add(new Line("Line 1"));
        lines.add(new Line("Line 2"));
        stop.setLineas(lines);

        assertEquals(lines, stop.getLineas());
    }

    @Test
    void testSetLineas() {
        Stop stop = new Stop();
        Line line1 = new Line(1, "Line 1", 10);
        Line line2 = new Line(2, "Line 2", 20);
        Line line3 = new Line(3, "Line 3", 30);

        List<Line> lineas = new ArrayList<>();
        lineas.add(line1);
        lineas.add(line2);

        stop.setLineas(lineas);
        stop.setLineas(Collections.singletonList(line3));

        assertEquals(1, stop.getLineas().size());
        assertFalse(stop.getLineas().contains(line1));
        assertFalse(stop.getLineas().contains(line2));
        assertTrue(stop.getLineas().contains(line3));
    }

    @Test
    void testToString1() {
    }
}



