package frc.robot.Auto.Paths;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;

import static frc.robot.Auto.FieldMap.*;

public class SmartTrajectoryBuilder {
    public enum Positions {
        LOADING, TARGET, WOF, TRENCH_FAR, TRENCH_CLOSE, SCORING_POSITION_RIGHT, SCORING_POSITION_LEFT
    }

    public enum FieldAreas {
        ALLIANCE_INITIATION, ALLIANCE_TRENCH, CLIMB_AREA, BETWEEN_ALLIANCE_CLIMB_INITIATION,
        BETWEEN_OTHER_CLIMB_INITIATION, OTHER_INITIATION, OTHER_TRENCH
    }

    public static TrajectoryContainer routeToPosition(Positions namedPos, Rotation2d endRotation) {
        return routeToPosition(new Pose2d(getWaypointFromPosition(namedPos), endRotation));
    }

    public static TrajectoryContainer routeToPosition(Pose2d endPosition) {
        ArrayList<Translation2d> interiorWaypoints = new ArrayList<>();

    }

    /**
     * Returns the FieldAreas that the Pose2d is in Uses values from
     * {@link FieldMap}
     * 
     * @param currentPose2d Pose2d that you want the FieldAreas of
     * @return FieldAreas that the Pose2d is in
     */
    public static FieldAreas getFieldArea(Pose2d currentPose2d) {
        if (currentPose2d.getX() < alliance_initationX) {
            return FieldAreas.ALLIANCE_INITIATION;
        } else if (currentPose2d.getX() > other_initationX) {
            return FieldAreas.OTHER_INITIATION;
        } else if (currentPose2d.getX() < trenchStartX) {
            return FieldAreas.BETWEEN_ALLIANCE_CLIMB_INITIATION;
        } else if (currentPose2d.getX() > trenchEndX) {
            return FieldAreas.BETWEEN_OTHER_CLIMB_INITIATION;
        } else if (currentPose2d.getY() < alliance_trenchY) {
            return FieldAreas.ALLIANCE_TRENCH;
        } else if (currentPose2d.getY() > other_trenchY) {
            return FieldAreas.OTHER_TRENCH;
        } else {
            return FieldAreas.CLIMB_AREA;
        }
    }

    // TODO
    public static boolean isAllianceSide(FieldAreas fieldArea) {

        return true;
    }

    private static Translation2d getWaypointFromPosition(Positions namedPos) {
        switch (namedPos) {
        case LOADING:
            return Waypoints.loadingPosition;
        case TARGET:
            return Waypoints.targetPosition;
        case WOF:
            return Waypoints.wofPosition;
        case TRENCH_FAR:
            return Waypoints.trenchFarPosition;
        case TRENCH_CLOSE:
            return Waypoints.trenchClosePosition;
        case SCORING_POSITION_RIGHT:
            return Waypoints.scoringRightPosition;
        case SCORING_POSITION_LEFT:
            return Waypoints.scoringLeftPosition;
        default:
            return null;
        }
    }
}
