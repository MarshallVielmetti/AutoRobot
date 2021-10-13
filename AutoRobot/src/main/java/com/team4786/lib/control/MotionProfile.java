package com.team4786.lib.control;
/**
 * Uses an interface to allow easy swapping between Trapezoidal and S-Curve
 * This is used to implement various motion profilers, which generate 1D position, velocity, and acceleration values
 * Along the spline curve
 * This information is then fed to another system to be mapped to the specific robot
 */
interface MotionProfile {
    /**
     * Returns flattened position along the spline at time t.
     * @param t Time to query curve for.
     * @return 1D position along spline curve
     */
    double queryPosition(double t);


    /**
     * Returns flattened position along the spline at time t.
     * @param t Time to query curve for.
     * @return 1D velocity along spline curve
     */
    double queryVelocity(double t);

    /**
     * Determines a time given a position along the spline
     * @param p double position along the spline
     * @return double time
     */
    double lookupScalarPosition(double p);


    /**
     * Returns flattened position along the spline at time t.
     * @param t Time to query curve for.
     * @return 1D acceleration along spline curve
     */
    double queryAcceleration(double t);

    double[] getTimeProfile();

}
