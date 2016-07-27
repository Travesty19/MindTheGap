package ca.ubc.cs.cpsc210.mindthegap.parsers;


import ca.ubc.cs.cpsc210.mindthegap.model.Branch;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Parser for route strings in TfL line data
 */
public class BranchStringParser {



    /**
     * Parse a branch string obtained from TFL
     *
     * @param branch branch string
     * @return list of lat/lon points parsed from branch string
     */
    public static List<LatLon> parseBranch(String branch) {
        List<LatLon> list = new ArrayList<LatLon>();
        double lat = 0.0;
        double lng = 0.0;

        String x = branch.substring(2, (branch.length() - 2));

        for (String s : x.split(",")) {
            if (s.charAt(0) == '[') {
                lng = Double.parseDouble(s.substring(1, (s.length())));
            } else {
                lat = Double.parseDouble(s.substring(0, (s.length() - 1)));
                LatLon latlon = new LatLon(lat, lng);
                list.add(latlon);
            }
        }
        return list;
    }
}