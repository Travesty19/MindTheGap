package ca.ubc.cs.cpsc210.mindthegap.tests.model;

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.model.StationManager;
import ca.ubc.cs.cpsc210.mindthegap.model.exception.StationException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for StationManager
 */
public class StationManagerTest {
    private StationManager stnManager;
    LatLon latlon1 = new LatLon(51.518251, -0.149719);
    LatLon latlon2 = new LatLon(51.617923, 0.07505100000000001);
    Station station1 = new Station("2062509008", "Oxford Circus Underground Station", latlon1);
    Station station2 = new Station("182136690", "Oxford Circus Underground Station", latlon2);
    Line line = new Line(LineResourceData.BAKERLOO, "2062509008", "Oxford Circus Underground Station");

    @Before
    public void setUp() {
        stnManager = StationManager.getInstance();
        stnManager.clearSelectedStation();
        stnManager.clearStations();
    }

    @Test
    public void testStationManagerConstructor() {
        assertEquals(stnManager.getSelected(), null);
    }

    @Test
    public void testAddStationOnLine() {
        line.addStation(station1);
        line.addStation(station2);
        stnManager.addStationsOnLine(line);
        assertEquals(2, stnManager.getNumStations());

        try {
            stnManager.setSelected(station1);
        }
        catch (StationException e){
            fail("Should not have had expection");
        }
        assertEquals("2062509008", stnManager.getSelected().getID());
        assertEquals(station1.getID(), stnManager.getStationWithId("2062509008").getID());
        stnManager.clearStations();
        assertEquals(0, stnManager.getNumStations());
    }

    @Test
    public void testFindNearestTo () {
        line.addStation(station1);
        line.addStation(station2);
        stnManager.addStationsOnLine(line);
        assertEquals(station1, stnManager.findNearestTo(new LatLon(51.518250, -0.149718)));
        assertEquals(station2, stnManager.findNearestTo(new LatLon(51.617924, 0.07505100000000002)));
        stnManager.clearStations();
        assertEquals(null, stnManager.findNearestTo(new LatLon(51.518250, -0.149718)));
    }
}