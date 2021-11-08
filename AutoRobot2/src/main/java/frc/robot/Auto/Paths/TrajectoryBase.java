package frc.robot.Auto.Paths;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;

/**
 * The purpose of this interface is to allow the definition of trajectories From
 * a file. The subclasses of this should be passed ot the DrivePathAction.
 * Allows for another layer of abstraction making the code appear simpler. It is
 * abstract because no instances of TrajectoryBase should ever be created
 */
public abstract class TrajectoryBase {
    TrajectoryContainer m_trajectoryContainer;

    public TrajectoryBase() {
        // This should call the buildTrajectoryContainer() method that is overridden
        // In the subclass
        // But if something is breaking it might be this
        this.m_trajectoryContainer = buildTrajectoryContainer();
    }

    public Trajectory getTrajectory() {
        return m_trajectoryContainer.getTrajectory();
    }

    // NEEDS TO BE OVERRIDDEN
    public abstract TrajectoryContainer buildTrajectoryContainer();
}
