// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants {

        // TODO - Input Correct IDs
        public static final int kLeftMotorID1 = 1;
        public static final int kLeftMotorID2 = 3;
        public static final int kLeftMotorID3 = 5;

        public static final int kRightMotorID1 = 2;
        public static final int kRightMotorID2 = 4;
        public static final int kRightMotorID3 = 6;

        // TODO - Motor Inversions
        public static final boolean kLeftInverted = true;
        public static final boolean kRightInverted = false;

        public static final boolean kLeftEncoderInverted = true;
        public static final boolean kRightEncoderInverted = false;

        // TODO - Measure robot
        public static final double kWheelBase = 0.3; // Meters
        public static final double kWheelRadius = 0.15; // Meters
        public static final double kGearReduction = 5;

        // TODO - Motor Feedforward Constants
        public static final double kS = 0.01;
        public static final double kV = 0.001;

        // TODO - Drive PID Constants
        public static final double kP = 0.01;
        public static final double kI = 0.0;
        public static final double kD = 0.0001;

        // RAMSETE Controller C onstants
        public static final double kRamseteB = 2.0;
        public static final double kRamseteZeta = 0.7;

        // TODO - Drive Constraints for Path Following
        public static final double kMaxVelocity = 13; // m/s
        public static final double kMaxAcceleration = 2; // m/2^s
        public static final double kMaxCentripetalAcceleration = 2; // m^s^2 / rad?
        public static final double kMaxVoltage = 9; // Less than nominal 12-13 for consistency

        // Motion Conersion Factors
        public static final double kDriveVelocityConversionFactor = 4096 * kGearReduction * 2 * kWheelRadius * Math.PI; // TODO
                                                                                                                        // -m/s
    }
}
