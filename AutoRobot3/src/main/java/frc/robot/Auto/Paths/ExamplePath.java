package frc.robot.Auto.Paths;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;

import static frc.robot.Auto.Paths.Waypoints.*;

/**
 * Example path. For use in the {@link DriveStandardMode} example autonomous
 * mode.
 */
public class ExamplePath extends PathBase {

    private static final Pose2d INITIAL_POSE2D = new Pose2d(W_STARTING_POSITION, new Rotation2d());
    private static final Translation2d[] WAYPOINTS = { W_MIDDLE_POSITION };
    private static final Pose2d END_POSE2D = new Pose2d(W_ENDING_POSITION, new Rotation2d());

    public ExamplePath() {
        super(INITIAL_POSE2D, WAYPOINTS, END_POSE2D);
    }
}
