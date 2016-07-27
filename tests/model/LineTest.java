package ca.ubc.cs.cpsc210.mindthegap.tests.model;

import ca.ubc.cs.cpsc210.mindthegap.model.Branch;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Travis on 2015-10-25.
 */
public class LineTest {

    private Line line;


    @Before
    public void setup() {

        line = new Line(LineResourceData.BAKERLOO, "2062509008", "Oxford Circus Underground Station");
    }

    @Test
    public void testGetName() {assertEquals("Oxford Circus Underground Station", line.getName());}

    @Test
    public void testGetId() {assertEquals("2062509008", line.getId());}

    @Test
    public void testGetColor() {assertEquals(0xFF894E24, line.getColour());}

    @Test
    public void testAddStation() {
        LatLon latlon1 = new LatLon(51.518251, -0.149719);
        LatLon latlon2 = new LatLon(51.617923, 0.07505100000000001);
        Station station1 = new Station("2062509008", "Oxford Circus Underground Station", new LatLon(51.518251, -0.149719));
        Station station2 = new Station("182136690", "Oxford Circus Underground Station", new LatLon(51.617923, 0.07505100000000001));

        assertEquals(0, line.sizeStation());
        line.addStation(station1);
        assertEquals(1, line.sizeStation());
        line.addStation(station2);
        assertEquals(2, line.sizeStation());
        assertTrue(line.hasStation(station1));
        assertTrue(line.hasStation(station2));
        line.removeStation(station2);
        assertFalse(line.hasStation(station2));
        assertTrue(line.hasStation(station1));
        assertEquals(1, line.sizeStation());
        line.clearStations();
        assertFalse(line.hasStation(station1));
        assertEquals(0, line.sizeStation());
    }

    @Test
    public void testEquals () {
        assertTrue(line.equals(line));
    }

    @Test
    public void testAddBranch () {
        Branch b1 = new Branch("[0.093493,51.6037]");
        Branch b2 = new Branch("[0.091015,51.5956]");
        line.addBranch(b1);
        line.addBranch(b2);
        assertEquals(2, line.getBranches().size());
    }

}