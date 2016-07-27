package ca.ubc.cs.cpsc210.mindthegap.tests.model;

import ca.ubc.cs.cpsc210.mindthegap.model.Arrival;
import ca.ubc.cs.cpsc210.mindthegap.model.ArrivalBoard;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Travis on 2015-10-22.
 */
public class ArrivalBoardTest {

    private ArrivalBoard ab;
    Line line1 = new Line(LineResourceData.BAKERLOO, "316525858", "Oxford Circus Underground Station");
    private List<Arrival> arrivals = new ArrayList<Arrival>();

    @Before
    public void setup() {

        ab = new ArrivalBoard(line1, "inbound");}

    @Test
    public void testGetTravelDirn() {
        assertEquals("inbound", ab.getTravelDirn());
    }

    @Test
    public void testGetLine() {
        assertEquals("316525858", ab.getLine().getId());
        assertEquals("Oxford Circus Underground Station", ab.getLine().getName());
    }

    @Test
    public void testGetNumArrivals() {
        Arrival arrival1 = new Arrival(60, "White City Underground Station", "Westbound - Platform 1");
        Arrival arrival2 = new Arrival(354, "Hainault Underground Station", "outbound");

        assertEquals(0, ab.getNumArrivals());
        ab.addArrival(arrival1);
        assertEquals(1, ab.getNumArrivals());
        ab.addArrival(arrival2);
        assertEquals(2, ab.getNumArrivals());
        assertTrue(ab.hasArrival(arrival1));
        assertTrue(ab.hasArrival(arrival2));
        ab.clearArrivals();
        assertFalse(ab.hasArrival(arrival1));

        assertEquals(0, ab.getNumArrivals());

    }

    @Test
    public void testEquals() {
        assertTrue(ab.equals(ab));
    }

}