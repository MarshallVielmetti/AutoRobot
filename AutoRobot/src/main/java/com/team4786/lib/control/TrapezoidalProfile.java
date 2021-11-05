package com.team4786.lib.control;

import com.team4786.lib.util.RobotContainer;
public class TrapezoidalProfile implements MotionProfile{

    private Spline spline; //the spline - might not need to store this?
    private double splineLength; //total length of the spline
    private double[] cumulativeLength; //cumulative length of the spline at every point
    private double[] timeProfile; //timestamps for when the robot hits every marker

    private double tAcc;
    private double vMax;
    private double aMax;

    private double aDist; //distnace spent accelerating



    /**
     * Initializes the motion profile using default velocity and acceleration
     * @param spline Spline to generate profile for
     */
    public TrapezoidalProfile(Spline spline) {
        this(spline, RobotContainer.maxVelocity, RobotContainer.maxAcceleration);
    }

    /**
     * Specifying Constructor - initializes motion profile using spline, vMax and TAcc
     * @param spline Spline to generate profile for
     * @param vMax Max desired velocity
     * @param aMax Max desired acceleration
     */
    public TrapezoidalProfile(Spline spline, double vMax, double aMax) {
        this.spline = spline;
        this.splineLength = spline.getSplineLength();
        this.cumulativeLength = spline.getCumulativeLength();
        this.timeProfile = this.generateTimeProfile();

        this.vMax = vMax;
        this.aMax = aMax;
        this.tAcc = vMax / aMax; // formula for tAcc given aMax and vMax (remember trapezoidal)

        this.aDist = (1/2)*vMax*tAcc;
    }


    /**
     * Creates timestamps for every time the robot will hit each marker.
     * @return double[] containing value of t at each position
     */
    private double[] generateTimeProfile() {
        return new double[1]; //TEMPORARY OBVIOUSLY
    }
    
    /**
     * returns the time profile for any external use
     * @return double[] timeProfile
     */
    public double[] getTimeProfile() {
        return this.timeProfile;
    }


    /**
     * Determines a time given a position along the spline
     * @param p double position along the spline
     * @return double time
     */
    public double lookupScalarPosition(double p) {
        if (p < 0) {
            return -1; //just in case
        }
        else if (p < this.aDist) {
            //Robot currently accelerating
            return this.getAccelTime(p);
        } else if (p < this.splineLength - this.aDist) {
            //Robot currently at max velocity
            return this.getLinearTime(p);
        } else if (p < this.splineLength) {
            //Robot currently deccelerating
            return this.getDecelTime(p);
        } 
        return -1;
    }

    /**
     * Finds time given position during acceleration portion of curve
     * @param p position to find time of
     * @return time
     */
    private double getAccelTime(double p) {
        /*
        from calculus, we know get position from velocity line with integral
        integral of straight line velocity (v = t*amax) gives p = (1/2)t^2 * amax
        solving for position yields : t = +sqrt(2p/amax)
        */
        return Math.sqrt(2*p / this.aMax); 
    }

    /**
     * Gets time given position for the linear portion of the curve
     * @param p position to find time of
     * @return time
     */
    private double getLinearTime(double p) {
            /*
            during constant v phase - p = pAcc + t*vmax, where pAcc is the 
            distance traveled while accelerating (1/2*vmax*tAcc)
            simplify by simply saying plin = p - pAcc
            to get time spent at max velocity : t = pLin / vMax
            so overall t values is t + tAcc -> pLin / vmax + tAcc = t
            */
            double pLin = p - this.aDist;
            return pLin / vMax + this.tAcc;
    }

    private double getDecelTime(double p) {
        //distance to accelerate = 1/2*vMax*amax
        double t0 = this.getLinearTime(p - this.aDist); //total time spent before beginning decelleration
        double pdec = p - this.splineLength + this.aDist; //total distance from beginning of decelleration
        //where tdec is the time spent decellerating, we know:
        //we know v(tdec) = vMax - tdec*aMax
        //which gives p(tDec) = vMax*t - (1/2)tDec^2 * aMax
        double tadd = 
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
