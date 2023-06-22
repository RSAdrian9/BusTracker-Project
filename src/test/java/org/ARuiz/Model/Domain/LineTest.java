package org.ARuiz.Model.Domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    @Test
    void testGetId_bus() {
        Line line = new Line(1, "Test Line", 10);
        assertEquals(1, line.getId_bus());
    }

    @Test
    void testSetId_bus() {
        Line line = new Line();
        line.setId_bus(1);
        assertEquals(1, line.getId_bus());
    }

    @Test
    void testGetName() {
        Line line = new Line(1, "Test Line", 10);
        assertEquals("Test Line", line.getName());
    }

    @Test
    void testSetName() {
        Line line = new Line();
        line.setName("New Line");
        assertEquals("New Line", line.getName());
    }

    @Test
    void testGetPlace() {
        Line line = new Line(1, "Test Line", 10);
        assertEquals(10, line.getPlace());
    }

    @Test
    void testSetPlace() {
        Line line = new Line();
        line.setPlace(20);
        assertEquals(20, line.getPlace());
    }

    @Test
    void testGetStops() {
        Line line = new Line();
        List<Stop> stops = new ArrayList<>();
        stops.add(new Stop("Stop 1"));
        stops.add(new Stop("Stop 2"));
        line.setStops(stops);

        assertEquals(stops, line.getStops());
    }

    @Test
    void testSetStops() {
        Line line = new Line();
        List<Stop> stops = new ArrayList<>();
        stops.add(new Stop("Stop 1"));
        stops.add(new Stop("Stop 2"));
        line.setStops(stops);

        assertEquals(stops, line.getStops());
    }

    @Test
    void testToString() {
        Line line = new Line(1, "Test Line", 10);
        String expected = "Lines -> 1 Test Line 10";
        assertEquals(expected, line.toString());
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getPlace() {
    }

    @Test
    void setPlace() {
    }

    @Test
    void getStops() {
    }

    @Test
    void setStops() {
    }

    @Test
    void testToString1() {
    }
}
