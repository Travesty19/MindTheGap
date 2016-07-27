package ca.ubc.cs.cpsc210.mindthegap.tests.model;

import ca.ubc.cs.cpsc210.mindthegap.model.Branch;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.junit.Test;
import org.junit.Before;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Travis on 2015-10-25.
 */
public class BranchTest {
    private Branch b;

    @Before
    public void setup() {b = new Branch("[[[0.093493,51.6037],[0.091015,51.5956],[0.088596,51.5857],[0.090015,51.5757],[0.066195,51.5765],[0.045369,51.5762],[0.028537,51.5755],[0.008202,51.5683],[-0.005515,51.5566],[-0.00345,51.5418],[-0.033633,51.5251],[-0.0555,51.5272],[-0.083176,51.5174],[-0.088948,51.5134],[-0.097562,51.5149],[-0.111578,51.5183],[-0.12047,51.5176],[-0.130406,51.5164],[-0.141899,51.5152],[-0.149719,51.5143],[-0.15895,51.5134],[-0.175491,51.5117],[-0.187149,51.5103],[-0.196102,51.5091],[-0.205677,51.5071],[-0.218812,51.5044],[-0.224295,51.512],[-0.247248,51.5166],[-0.259754,51.5235],[-0.292704,51.5302],[-0.323447,51.5367],[-0.346052,51.5424],[-0.368702,51.5482],[-0.398904,51.5569],[-0.410699,51.5607],[-0.437875,51.5697]]]");}

    @Test
    public void testBranch() {
        LatLon latlon = new LatLon(51.6037, 0.093493);
        LatLon latlon2 = new LatLon(51.5697,-0.437875);
        assertEquals(latlon, b.getPoints().get(0));
        assertEquals(latlon2, b.getPoints().get((b.getPoints().size() - 1)));
    }

    @Test
    public void testEquals () {
        assertTrue(b.equals(b));
    }
}