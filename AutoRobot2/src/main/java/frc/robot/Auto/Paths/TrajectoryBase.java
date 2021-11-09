package frc.robot.Auto.Paths;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import frc.robot.Auto.StartingPosition;

/**
 * The purpose of this interface is to allow the definition of trajectories From
 * a file. The subclasses of this should be passed ot the DrivePathAction.
 * Allows for another layer of abstraction making the code appear simpler. It is
 * abstract because no instances of TrajectoryBase should ever be created
 */
public abstract class TrajectoryBase {
    private TrajectoryContainer m_trajectoryContainer;

    public TrajectoryBase() {
        // This should call the buildTrajectoryContainer() method that is overridden
        // In the subclass
        // But if something is breaking it might be this
        this.m_trajectoryContainer = buildTrajectoryContainer();
    }

    /**
     * Should only be used if constructing one of these with a given starting
     * position. As in it is the first trajectory called
     * 
     * @param startPos
     */
    public TrajectoryBase(StartingPosition startPos) {
        this.m_trajectoryContainer = buildTrajectoryContainer(startPos);
    }

    public TrajectoryBase(Translation2d startTranslation2d) {

    }

    /**
     * Used to get the trajectory from the trajectory container
     * 
     * @return a {@link Trajectory} defined by the @see buildTrajectoryContainer
     *         command
     */
    public Trajectory getTrajectory() {
        return m_trajectoryContainer.getTrajectory();
    }

    // NEEDS TO BE OVERRIDDEN
    /**
     * Used to basically define everything
     * 
     * @return the {@link TrajectoryContainer} that defines the trajectory
     */
    public abstract TrajectoryContainer buildTrajectoryContainer();

    /**
     * Only use if this is the first trajectory called.
     * 
     * @param startPos StartingPosition to be used to determine which container is
     *                 used
     * @return TrajectoryContainer
     */
    public abstract TrajectoryContainer buildTrajectoryContainer(StartingPosition startPos);
}
