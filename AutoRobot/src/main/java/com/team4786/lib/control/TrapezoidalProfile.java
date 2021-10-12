package com.team4786.lib.control;

public class TrapezoidalProfile implements MotionProfile{

    private Spline spline; //the spline - might not need to store this?
    private double splineLength; //total length of the spline
    private double[] cumulativeLength; //cumulative length of the spline at every point
    private double[] timeProfile; //timestamps for when the robot hits every marker
    /**
     * Initializes the motion profile
     * @param spline Spline to generate profile for
     */
    public TrapezoidalProfile(Spline spline) {
        this.spline = spline;
        this.splineLength = spline.getSplineLength();
        this.cumulativeLength = spline.getCumulativeLength();
        this.timeProfile = this.mapTimeProfile();
    }


    /**
     * Creates timestamps for every time the robot will hit each marker.
     * @return double[] containing value of t at each position
     */
    public double[] generateTimeProfile() {
        return new double[1]; //TEMPORARY OBVIOUSLY
    }

    /**
     * Returns flattened position along the spline at time t.
     * @param t Time to query curve for.
     * @return 1D position along spline curve
     */
    public double queryPosition(double t) {

    }


    /**
     * Returns flattened position along the spline at time t.
     * @param t Time to query curve for.
     * @return 1D velocity along spline curve
     */
    double queryVelocity(double t) {

    }


    /**
     * Returns flattened position along the spline at time t.
     * @param t Time to query curve for.
     * @return 1D acceleration along spline curve
     */
    double queryAcceleration(double t) {

    }

    // END REQUIREMENTS BY INTERFACE
}
