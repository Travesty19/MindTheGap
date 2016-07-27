package ca.ubc.cs.cpsc210.mindthegap.model;

import java.util.*;

/**
 * Represents a line on the underground with a name, id, list of stations and list of branches.
 *
 * Invariants:
 * - no duplicates in list of stations
 * - iterator iterates over stations in the order in which they were added to the line
 */
public class Line implements Iterable<Station> {
    private List<Station> stns = new ArrayList<Station>();
    private String id;
    private String name;
    private LineResourceData lmd;
    Set<Branch> branches = new HashSet<Branch>();

    /**
     * Constructs a line with given resource data, id and name.
     * List of stations and list of branches are empty.
     *
     * @param lmd     the line meta-data
     * @param id      the line id
     * @param name    the name of the line
     */
    public Line(LineResourceData lmd, String id, String name) {
        this.id = id;
        this.name = name;
        this.lmd = lmd;

    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    /**
     * Get colour specified by line resource data
     *
     * @return  colour in which to plot this line
     */
    public int getColour() {
        return lmd.getColour();
    }

    /**
     * Add station to line.
     *
     * @param stn  the station to add to this line
     */
    public void addStation(Station stn) {
        System.out.println("Adding station - "+ stn.getID());
        stns.add(stn);
    }

    public int sizeStation() {
        return stns.size();
    }

    /**
     * Remove station from line
     *
     * @param stn  the station to remove from this line
     */
    public void removeStation(Station stn) {
        //search stn by id and remove the object
         String id= stn.getID();

        for(int i=0;i<stns.size();i++){
            if ((stns.get(i).getID()).equals(id)) {
                System.out.println("found station ");
                stns.remove(i);
            }
        }

    }

    /**
     * Clear all stations from this line
     */
    public void clearStations() {
        stns.clear();
    }

    public List<Station> getStations() {

        Set setItems = new LinkedHashSet(stns);
        stns.clear();
        stns.addAll(setItems);
        return stns;   // stub
    }

    /**
     * Determines if this line has a stop at a given station
     *
     * @param stn  the station
     * @return  true if line has a stop at given station
     */
    public boolean hasStation(Station stn) {

        String id = stn.getID();
        for(Station s:stns){
            if((s.getID()).equals(id))
                return true;
        }
        return false;
    }

    /**
     * Add a branch to this line
     *
     * @param b  the branch to add to line
     */
    public void addBranch(Branch b) {

        branches.add(b);
    }

    public Set<Branch> getBranches() {
        return branches;  // stub
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line stations = (Line) o;

        if (!id.equals(stations.id)) return false;
        if (!name.equals(stations.name)) return false;
        return lmd == stations.lmd;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + lmd.hashCode();
        return result;
    }

    @Override
    public Iterator<Station> iterator() {
        // Do not modify the implementation of this method!
        return stns.iterator();
    }
}
