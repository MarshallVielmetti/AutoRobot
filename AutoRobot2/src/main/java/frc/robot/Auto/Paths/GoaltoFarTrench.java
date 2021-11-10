package frc.robot.Auto.Paths;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.Auto.StartingPosition;

/**
 * Path from the Waypoint targetPosition to the waypoint trenchFarPosition
 */
public class GoaltoFarTrench extends TrajectoryBase {
    public GoaltoFarTrench() {
        super();
    }

    @Override
    public TrajectoryContainer buildTrajectoryContainer() {
        Pose2d initialPose = new Pose2d(Waypoints.targetPosition, new Rotation2d(0));
        ArrayList<Translation2d> interiorWaypoints = new ArrayList<>();
        interiorWaypoints.add(Waypoints.scoringRightPosition);
        Pose2d endingPose = new Pose2d(Waypoints.trenchFarPosition, new Rotation2d(Math.PI));

        return new TrajectoryContainer(initialPose, interiorWaypoints, endingPose);
    }

    /**
     * This should never be called.
     */
    @Override
    public TrajectoryContainer buildTrajectoryContainer(StartingPosition startPos) {
        return null;
    }
}
