package ca.ubc.cs.cpsc210.mindthegap.tests.model;


import ca.ubc.cs.cpsc210.mindthegap.model.Arrival;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Travis on 2015-10-22.
 */
import static org.junit.Assert.*;

public class ArrivalTest {

    private int timeToStation = 60;
    private String destination = "White City Underground Station";
    private String platform = "Westbound - Platform 1";
    private Arrival a;

    @Before
    public void setup() {a = new Arrival(timeToStation, destination, platform);}

    @Test
    public void testGetTravelDirection() { assertEquals("Westbound", a.getTravelDirn()); }

    @Test
    public void testGetPlatformName() { assertEquals("Platform 1", a.getPlatformName());}

    @Test
    public void testGetTimeToStationInMins() { assertEquals(1, (a.getTimeToStationInMins()));}

    @Test
    public void testGetDestination() { assertEquals("White City Underground Station", a.getDestination());}

    @Test
    public void testGetPlatform() { assertEquals("Westbound - Platform 1", a.getPlatform());}
}