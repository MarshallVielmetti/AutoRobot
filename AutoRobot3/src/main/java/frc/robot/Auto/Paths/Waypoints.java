package frc.robot.Auto.Paths;

import edu.wpi.first.wpilibj.geometry.Translation2d;

import frc.robot.Auto.Paths.PathBase;
import frc.robot.commands.DrivePathCommand;

/**
 * Container class to hold a bunch of field waypoints
 * Ideally make this work with an external path builder for ease of use but
 * would be an increase in complexity
 * 
 * @see PathBase
 * @see DrivePathCommand
 */
public final class Waypoints {
    // Examples
    public static final Translation2d W_STARTING_POSITION = new Translation2d(15, 15);
    public static final Translation2d W_MIDDLE_POSITION = new Translation2d(25, 25);
    public static final Translation2d W_ENDING_POSITION = new Translation2d(35, 35);
}
