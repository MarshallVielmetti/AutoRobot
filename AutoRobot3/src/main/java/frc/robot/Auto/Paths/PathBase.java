package frc.robot.Auto.Paths;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveKinematicsConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.subsystems.DriveSubsystem;

import static frc.robot.Constants.DriveConstants.*;

import java.util.Arrays;

import java.util.ArrayList;

/**
 * Holds all of the reusable stuff that will be used by every trajectory
 * This should then be subclassed as seen in {@link ExamplePath.java}
 * For use with the RamseteCommand, specifically the
 * {@link DrivePathCommand.java}
 * 
 * @see Trajectory
 */
public class PathBase {

    private final Trajectory m_trajectory;

    private static final DriveSubsystem m_drive = DriveSubsystem.getInstance(); // Forbidden technique

    /**
     * Define drive controller constraints
     * Note that these are all static and final which is why
     * I used the getInstance() call above
     * Avoids having to spend nay computing resources recalculating this at runtime.
     * 
     * @see CentripetalAccelerationConstraint
     * @see DifferentialDriveKinematicsConstraint
     * @see DifferentialDriveVoltageConstraint
     */
    private static final CentripetalAccelerationConstraint CENTRIPETAL_ACCELERATION_CONSTRAINT = new CentripetalAccelerationConstraint(
            kMaxCentripetalAcceleration);
    private static final DifferentialDriveKinematicsConstraint DIFFERENTIAL_DRIVE_KINEMATICS_CONSTRAINT = new DifferentialDriveKinematicsConstraint(
            m_drive.getDriveKinematics(), kMaxVelocity);
    private static final DifferentialDriveVoltageConstraint DIFFERENTIAL_DRIVE_VOLTAGE_CONSTRAINT = new DifferentialDriveVoltageConstraint(
            m_drive.getFeedForward(), m_drive.getDriveKinematics(), kMaxVoltage);

    private static final TrajectoryConfig TRAJECTORY_CONFIG = new TrajectoryConfig(kMaxVelocity, kMaxAcceleration)
            .addConstraint(CENTRIPETAL_ACCELERATION_CONSTRAINT).addConstraint(DIFFERENTIAL_DRIVE_KINEMATICS_CONSTRAINT)
            .addConstraint(DIFFERENTIAL_DRIVE_VOLTAGE_CONSTRAINT);

    /**
     * Constructor
     */
    public PathBase(Pose2d initialPose, Translation2d[] interiorPoints, Pose2d endingPose) {
        this.m_trajectory = TrajectoryGenerator.generateTrajectory(initialPose, Arrays.asList(interiorPoints),
                endingPose,
                TRAJECTORY_CONFIG);
    }

    public Trajectory getTrajectory() {
        return this.m_trajectory;
    }

}
