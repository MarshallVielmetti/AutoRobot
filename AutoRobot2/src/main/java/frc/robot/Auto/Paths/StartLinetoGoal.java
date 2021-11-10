package frc.robot.Auto.Paths;

import java.util.ArrayList;

import javax.swing.TransferHandler.TransferSupport;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.Auto.StartingPosition;
import frc.robot.subsystems.DriveSubsystem;

public class StartLinetoGoal extends TrajectoryBase {
    /**
     * Probably redundant. Ideally the super will call the buildTrajectoryContainer
     * method defined below.
     */

    /**
     * Use this constructor if this Trajectory is used first Changes which waypoints
     * it will pass through based on the robot's start position
     * 
     * @param startPosition Enum containing where the robot is starting
     */
    public StartLinetoGoal(StartingPosition startPosition) {
        super(startPosition);
    }

    @Override
    /**
     * This should never be called on this path.
     */
    public TrajectoryContainer buildTrajectoryContainer() {
        return null;
    }

    @Override
    /**
     * This method is a start line method, so you must specify the StartingPosition
     * enum.
     */
    public TrajectoryContainer buildTrajectoryContainer(StartingPosition startPos) {
        ArrayList<Translation2d> interiorWaypoints = new ArrayList<>();
        switch (startPos) {
        case RIGHT:
            interiorWaypoints.add(Waypoints.middleStartingPosition);
        case MIDDLE:
            interiorWaypoints.add(Waypoints.infrontOfTargetPosition);
        case LEFT:
            interiorWaypoints.add(Waypoints.infrontOfTargetPosition);
        }

        Pose2d startingPose = new Pose2d(Waypoints.getStartPositionFromEnum(startPos), new Rotation2d(0));
        Pose2d endingPose = new Pose2d(Waypoints.targetPosition, new Rotation2d(0));

        return new TrajectoryContainer(startingPose, interiorWaypoints, endingPose);
    }

}
