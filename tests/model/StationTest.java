package ca.ubc.cs.cpsc210.mindthegap.tests.model;

import ca.ubc.cs.cpsc210.mindthegap.model.Arrival;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Travis on 2015-10-25.
 */
public class StationTest {
    LatLon latlon1 = new LatLon(51.518251, -0.149719);
    LatLon latlon2 = new LatLon(51.617923, 0.07505100000000001);
    Station station;
    Station station2 = new Station("182136690", "Oxford Circus Underground Station", latlon2);


    @Before
    public void setup() {
        station = new Station("2062509008", "Oxford Circus Underground Station", latlon1);
    }

    @Test
    public void testGetName() {
        assertEquals ("Oxford Circus Underground Station", station.getName());
    }

    @Test
    public void testGetId() {
        assertEquals("2062509008", station.getID());
    }

    @Test
    public void testGetLoc() {
        assertEquals(latlon1, station.getLocn());
    }



    @Test
    public void testAddLine() {
        Line line1 = new Line(LineResourceData.BAKERLOO, "2062509008", "Oxford Circus Underground Station");
        Line line2 = new Line(LineResourceData.CENTRAL, "Epping Underground Station", "940GZZLUOXC");

        station.addLine(line1);
        assertTrue(station.hasLine(line1));
        station.addLine(line2);
        assertTrue(station.hasLine(line2));
        station.removeLine(line2);
        assertFalse(station.hasLine(line2));
        assertTrue(station.hasLine(line1));


    }

    @Test
    public void testAddArrival() {
        Arrival a1 = new Arrival(60, "White City Underground Station", "Westbound - Platform 1");
        Arrival a2 = new Arrival(120, "Epping Underground Station", "Eastbound - Platform 2");
        Line line1 = new Line(LineResourceData.BAKERLOO, "2062509008", "Oxford Circus Underground Station");
        Line line2 = new Line(LineResourceData.CENTRAL, "Epping Underground Station", "940GZZLUOXC");

        station.addArrival(line1, a1);
        assertEquals(1, station.getNumArrivalBoards());
        station.addArrival(line2, a2);
        assertEquals(2, station.getNumArrivalBoards());
        station.clearArrivalBoards();
        assertEquals(0, station.getNumArrivalBoards());
    }

    @Test
    public void testEquals () {
        assertTrue(station.equals(station));
    }

}