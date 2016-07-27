package ca.ubc.cs.cpsc210.mindthegap.parsers;

import ca.ubc.cs.cpsc210.mindthegap.model.Arrival;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLArrivalsDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Set;

/**
 * A parser for the data returned by the TfL station arrivals query
 */
public class TfLArrivalsParser extends TfLAbstractParser {


    /**
     * Parse arrivals from JSON response produced by TfL query.  All parsed arrivals are
     * added to given station assuming that corresponding JSON object as all of:
     * timeToStation, platformName, lineID and one of destinationName or towards.  If
     * any of the aforementioned elements is missing, the arrival is not added to the station.
     *
     * @param stn          station to which parsed arrivals are to be added
     * @param jsonResponse the JSON response produced by TfL
     * @throws JSONException                   when JSON response does not have expected format
     * @throws TfLArrivalsDataMissingException when all arrivals are missing at least one of the following:
     *                                         <ul>
     *                                         <li>timeToStation</li>
     *                                         <li>platformName</li>
     *                                         <li>lineId</li>
     *                                         <li>destinationName and towards</li>
     *                                         </ul>
     */
    public static void parseArrivals(Station stn, String jsonResponse)
            throws JSONException, TfLArrivalsDataMissingException {
        JSONArray ja = new JSONArray(jsonResponse);
        for (int j = 0; j < ja.length(); j++) {
            JSONObject jo = ja.getJSONObject(j);
            String detinationOrTowards;
            if (jo.isNull("destinationName")) detinationOrTowards = jo.getString("towards");
            else {
                detinationOrTowards = jo.getString("towards");
            }
            Arrival arrival = new Arrival(jo.getInt("timeToStation"), detinationOrTowards, jo.getString("platformName"));
            if (jo.isNull("timeToStation") || jo.isNull("destinationName") || jo.isNull("platformName") || jo.isNull("towards")) {
                throw new TfLArrivalsDataMissingException("");
            }
            for (Line l : stn.getLines()) {
                if (l.getId().equalsIgnoreCase(jo.getString("lineId"))) {
                    if (jo.isNull("lineId"))
                        throw new TfLArrivalsDataMissingException("");
                    stn.addArrival(l, arrival);


                }
            }
        }

    }
}