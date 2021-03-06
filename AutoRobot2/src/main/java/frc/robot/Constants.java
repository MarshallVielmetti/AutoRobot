// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.geometry.Translation2d;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final double maxAutoVelocity = 15; // Meters per second
    public static final double maxAutoAcceleration = 2; // Meters per second^2

    public static final double maxAutoWheelVelocity = 15; // Meters per second
    public static final double maxCentripetalAcceleration = 5; // Meters per second^2

    public static final double distanceBetweenWheels = 0.8; // Meters

    // DETERMINED WITH ROBOT CHARACTERIZATION. ROBOT SPECFICIC.
    public static final double kP = 0.1; // Maybe the same as kS I'm not sure tbh.
    public static final double kPDriveVel = 0.1; // Could honestly be the same as the thing above

    public static final double kS = 0.1;
    public static final double kV = 0.5;
    public static final double kA = 0.01;

    // Should be robot agnostic. Defined @
    // https://docs.wpilib.org/en/stable/docs/software/advanced-controls/trajectories/ramsete.html
    public static final double kRamseteB = 2.0;
    public static final double kRamseteZeta = 0.7;

    public static final double maxVoltage = 10;

    // https://docs.wpilib.org/en/stable/docs/software/examples-tutorials/trajectory-tutorial/creating-drive-subsystem.html#encoder-accessor-method
    // Should be 4 times the value reported by the drive characterization tool.
    // Distance should be measured in meters
    public static final double kEncoderDistancePerPulse = 0.02;

}
