package com.team4786.robot.Trajectories;

import edu.wpi.first.wpilibj.geometry.Translation2d;

/**
 * This is a class used to contain a set of waypoints from around the field to be used for navigation.
 * All Paths will be created by routing the robot through these points
 * This will allow for dynamic routing in the future.
 * THESE ARE CONFIGURED FOR INFINITE RECHARGE
 * ALL UNITS IN METERS, RELATIVE TO BOTTOM RIGHT CORNER OF FIELD
 * https://docs.wpilib.org/en/stable/docs/software/advanced-controls/geometry/coordinate-systems.html
 * https://docs.wpilib.org/en/stable/docs/software/advanced-controls/geometry/pose.html 
 */
public class Waypoints {
    public static Translation2d pointGrid[][] = new Translation2d[5][15]; 

    //Starting Positions
    public static Translation2d startL = new Translation2d(); //Left Start
    public static Translation2d startM = new Translation2d(); //Middle Start
    public static Translation2d startR = new Translation2d(); //Right Start

    //Trench Waypoints
    //l = left side of field (relative to driver station)
    //r = right side of field (relative to driver station)
    public static Translation2d lTrenchClose = new Translation2d();
    public static Translation2d lTrenchUnder = new Translation2d();
    public static Translation2d lTrenchFar = new Translation2d();
    public static Translation2d rTrenchClose = new Translation2d();
    public static Translation2d rTrenchUnder = new Translation2d();
    public static Translation2d rTrenchFar = new Translation2d();

    //Scoring Waypoints
    //c = close side of field (relative)
    //f = far side of field
    public static Translation2d cPortal = new Translation2d();
    public static Translation2d cLoading = new Translation2d();
    public static Translation2d fPortal = new Translation2d();
    public static Translation2d fLoading = new Translation2d();

    //Other Waypoints of Use
    public static Translation2d optimalShooting1 = new Translation2d();
    public static Translation2d optimalShooting2 = new Translation2d();
}
