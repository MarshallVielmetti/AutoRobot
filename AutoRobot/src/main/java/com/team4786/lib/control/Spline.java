package com.team4786.lib.control;

import java.util.ArrayList;
import com.team4786.lib.util.Waypoint;
import com.team4786.lib.util.Vector2D;

public class Spline { 
    private ArrayList<Waypoint> waypoints;
    private ArrayList<Vector2D> markers;

    private static final int markerFidelity = 200; //Number of markers to generate for each spline

    /**
     * Constructs a new Spline class
     * @param waypoints ArrayList of Waypoints that define the spline
     */
    public Spline(ArrayList<Waypoint> waypoints) {
        this.waypoints = waypoints;
        this.calculateMarkers();
    }

    /**
     * Generates cubic hermite splines from the set of waypoints.
     * Does this by concatenating splines generated betweeh the different waypoints.
     */
    private void calculateMarkers() {
        markers.clear(); //clears existing arraylist of markers
        for (int i = 0; i < waypoints.size() - 1; i++) {
            markers.addAll(Spline.hermiteSpline(waypoints.get(i), waypoints.get(i+1)));
        }
    }

    /**
     * Calculates markers along a cubic hermite spline between w1 and w2
     * @param w1 First Waypoint
     * @param w2 Second Waypoint
     * @return ArrayList of Vectors to be appended to the markers Arraylist
     */
    private static ArrayList<Vector2D> hermiteSpline(Waypoint w1, Waypoint w2) {
        ArrayList<Vector2D> newMarkers = new ArrayList<Vector2D>();

        Vector2D p0 = w1.getPosition();
        Vector2D p1 = w2.getPosition();
        Vector2D v0 = w1.getVelocity();
        Vector2D v1 = w2.getVelocity();

        //(2*t**3 - 3*t**2 + 1)*p0[d] + (t**3 - 2*t**2 + t)*v0[d] + (-2*t**3 + 3*t**2)*p1[d] + (t**3 - t**2)*v1[d]
        //this is the equation for a cubic hermite spline that is being used for the vecs2add line

        for (int i = 0; i < markerFidelity; i++) {
            int t = i / markerFidelity; //Spline calculations take place from 0 to 1 -> fidelity determines how many points are calcualted
            
            Vector2D[] vecs2add = {p0.getScaled(2*Math.pow(t, 3) - 3*Math.pow(t, 2) + 1), v0.getScaled(Math.pow(t, 3) - 2*Math.pow(t, 2) + t), p1.getScaled(-2*Math.pow(t, 3) + 3*Math.pow(t, 2)), v1.getScaled(Math.pow(t, 3) - Math.pow(t, 2))};
            newMarkers.add(Vector2D.addArray(vecs2add)); //TO DO dumb error
        }

        return newMarkers;

    }

    public ArrayList<Vector2D> getMarkers() {
        return this.markers;
    }

    public void setWaypoints(ArrayList<Waypoint> waypoints) {
        this.waypoints = waypoints;
        this.calculateMarkers();
    }

    /**
     * Returns a scalar value for the length of the spline
     * @return double length of spline
     */
    public double getSplineLength() {
        double splineLength = this.markers.get(0).getMagnitude();
        for (int i = 1; i < this.markers.size(); i++) {
            splineLength += this.markers.get(i).getSubtract(this.markers.get(i-1)).getMagnitude();
        }

        return splineLength;
    }

    public double[] getCumulativeLength() {
        double[] splineLength = new double[this.markers.size()];
        splineLength[0] = this.markers.get(0).getMagnitude();
        for (int i = 1; i < this.markers.size(); i++) {
            splineLength[i] = splineLength[i - 1] + this.markers.get(i).getSubtract(this.markers.get(i-1)).getMagnitude();
        }

        return splineLength;
    }
}
