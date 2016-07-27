package ca.ubc.cs.cpsc210.mindthegap.tests.parsers;

import ca.ubc.cs.cpsc210.mindthegap.TfL.DataProvider;
import ca.ubc.cs.cpsc210.mindthegap.TfL.FileDataProvider;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.parsers.BranchStringParser;
import ca.ubc.cs.cpsc210.mindthegap.parsers.TfLLineParser;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLLineDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Unit test for TfLLineParser
 */
public class TfLLineParserBasicTest {
    private String lineData;
    private List<Line> list;

    @Before
    public void setUp() throws Exception {
        list = new ArrayList<Line>();
        DataProvider dataProvider = new FileDataProvider("./res/raw/central_inbound.json");
        lineData = dataProvider.dataSourceToString();
    }

    @Test
    public void testBasicLineParsing() {
        try {
            TfLLineParser.parseLine(LineResourceData.CENTRAL, lineData);
        } catch (JSONException e) {
            fail("JSONException should not have been thrown while parsing data in central_inbound.json");
        } catch (TfLLineDataMissingException e) {
            fail("TfLLineDataMissingException should not have been thrown while parsing data in central_inbound.json");
        }
    }

    @Test
    public void testParseLine () throws JSONException, TfLLineDataMissingException {
        assertEquals("central", TfLLineParser.parseLine(LineResourceData.CENTRAL, lineData).getId());
        assertEquals("Central", TfLLineParser.parseLine(LineResourceData.CENTRAL, lineData).getName());
        assertEquals(49, TfLLineParser.parseLine(LineResourceData.CENTRAL, lineData).sizeStation());
        assertEquals("940GZZLURBG", TfLLineParser.parseLine(LineResourceData.CENTRAL, lineData).getStations().get(5).getID());
        assertEquals("Redbridge", TfLLineParser.parseLine(LineResourceData.CENTRAL, lineData).getStations().get(5).getName());
        assertEquals("940GZZLUWSD", TfLLineParser.parseLine(LineResourceData.CENTRAL, lineData).getStations().get(6).getID());
        assertEquals("Wanstead", TfLLineParser.parseLine(LineResourceData.CENTRAL, lineData).getStations().get(6).getName());
        assertEquals(6, TfLLineParser.parseLine(LineResourceData.CENTRAL, lineData).getBranches().size());
    }



    // The single test above is very basic - it simply checks that your parser
    // doesn't throw an exception.
}