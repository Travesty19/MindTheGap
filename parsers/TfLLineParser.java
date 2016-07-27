package ca.ubc.cs.cpsc210.mindthegap.parsers;

import ca.ubc.cs.cpsc210.mindthegap.model.*;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLLineDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A parser for the data returned by TFL line route query
 */
public class TfLLineParser extends TfLAbstractParser {


    /**
     * Parse line from JSON response produced by TfL.  No stations added to line if TfLLineDataMissingException
     * is thrown.
     *
     * @param lmd line meta-data
     * @return line parsed from TfL data
     * @throws JSONException               when JSON data does not have expected format
     * @throws TfLLineDataMissingException when
     *                                     <ul>
     *                                     <li> JSON data is missing lineName, lineId or stopPointSequences elements </li>
     *                                     <li> for a given sequence: </li>
     *                                     <ul>
     *                                     <li> the stopPoint array is missing </li>
     *                                     <li> all station elements are missing one of name, lat, lon or stationId elements </li>
     *                                     </ul>
     *                                     </ul>
     */
    public static Line parseLine(LineResourceData lmd, String jsonResponse)
            throws JSONException, TfLLineDataMissingException {

        JSONObject jo = new JSONObject(jsonResponse);
        JSONArray ja = jo.getJSONArray("stopPointSequences");

        Line line = new Line(lmd, jo.getString("lineId"), jo.getString("lineName"));
        Station station;

        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo2 = ja.getJSONObject(i);

            JSONArray ja2 = jo2.getJSONArray("stopPoint");
            for (int j = 0; j < ja2.length(); j++) {
                JSONObject jo3 = ja2.getJSONObject(j);
                station = new Station(jo3.getString("stationId"), parseName(jo3.getString("name")), new LatLon(jo3.getDouble("lat"), jo3.getDouble("lon")));
                line.addStation(station);

                Set setItems = new LinkedHashSet(line.getStations());
                line.getStations().clear();
                line.getStations().addAll(setItems);

                JSONArray ja3 = jo.getJSONArray("lineStrings");
                for (int k = 0; k < ja3.length(); k++) {
                    String l = ja3.getString(k);
                    line.addBranch(new Branch(l));
                    String x = line.getBranches().toString();
                    System.out.println(x);
                    String y = line.getStations().toString();
                    System.out.println(y);

                }


            }

        }
        return line;
    }

}