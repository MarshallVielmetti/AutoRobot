package frc.robot.Auto.Paths;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveKinematicsConstraint;

import static frc.robot.Constants.DriveConstants.*;

/**
 * Holds all of the reusable stuff that will be used by every trajectory
 * This should then be subclassed as seen in {@link ExamplePath.java}
 * For use with the RamseteCommand, specifically the
 * {@link DrivePathCommand.java}
 * 
 * @see Trajectory
 */
public class PathBase extends Trajectory {
    private static final TrajectoryConfig TRAJECTORY_CONFIG = new TrajectoryConfig(kMaxVelocity, kMaxAcceleration);
    private static final CentripetalAccelerationConstraint CENTRIPETAL_ACCELERATION_CONSTRAINT = new CentripetalAccelerationConstraint(
            kMaxCentripetalAcceleration);

    private static final DifferentialDriveKinematicsConstraint DIFFERENTIAL_DRIVE_KINEMATICS_CONSTRAINT = new DifferentialDriveKinematicsConstraint(kinematics, maxSpeedMetersPerSecond)

    public PathBase()
    {

    }
}
