package frc.robot.Auto.Paths;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import frc.robot.subsystems.DriveSubsystem;

import static frc.robot.Auto.FieldMap.*;

/**
 * SUPER BASIC SMART TRAJECTORY ROUTER There are basically no safeguard built
 * into this, everything is hardcoded etc. I wouldn't recommend using this right
 * now just in case someone is ever looking at this.
 * 
 * The ultimate goal with this is to implement some kind of graph based
 * pathfinding algorithm.
 */
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

    public static TrajectoryContainer routeToPosition(Pose2d endPose2d) {
        // Array List to Contain all of the Interior Waypoints to be used
        ArrayList<Translation2d> interiorWaypoints = new ArrayList<>();

        // Current position as reported by the DriveSubsystem
        Pose2d initialPose2d = DriveSubsystem.getInstance().getCurrentPose2d();

        // Get the areas of the field the robot is on.
        FieldAreas currentFieldArea = getFieldArea(initialPose2d);
        FieldAreas targetFieldArea = getFieldArea(endPose2d);

        // Determines which type of path needs to be made
        if (isCloseAllianceSide(currentFieldArea) == isCloseAllianceSide(targetFieldArea)) {
            // Most simple scenario - the two waypoints are on the same 'side' of the field
            // In this scenario, you should be able to path the robot directly to the other
            // one.
            // Add one interior waypoint in the middle to make sure the robot takes a
            // reasonable path.
            return buildSimpleCaseContainer(initialPose2d, endPose2d);
        } else if (willRouteThroughTrench(currentFieldArea, targetFieldArea)) {

            return buildThroughTrenchContainer(initialPose2d, endPose2d);
        } else {
            // TODO
            return null;
        }
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
    public static boolean isCloseAllianceSide(FieldAreas fieldArea) {
        if (fieldArea == FieldAreas.ALLIANCE_INITIATION || fieldArea == FieldAreas.BETWEEN_ALLIANCE_CLIMB_INITIATION)
            return true;

        return false;
    }

    // A path will route THROUGH the entire trench IFF the areas are on different
    // sides of the field and not in the trench area.
    public static boolean willRouteThroughTrench(FieldAreas startingArea, FieldAreas endingArea) {
        if (isInTrench(startingArea) || isInTrench(endingArea)) {
            return false;
        } else if (isCloseAllianceSide(startingArea) == isCloseAllianceSide(endingArea)) {
            return false;
        } else if (startingArea == FieldAreas.CLIMB_AREA || endingArea == FieldAreas.CLIMB_AREA) {
            return false;
        }

        return true;
    }

    public static boolean isInTrench(FieldAreas area) {
        if (area == FieldAreas.ALLIANCE_TRENCH || area == FieldAreas.OTHER_TRENCH)
            return true;
        return false;
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

    /**
     * Rudimentary version of creating a path between the two points
     * 
     * @see TrajectoryContainer
     * @param initialPose2d Initial pose
     * @param endPose2d     Ending pose
     * @return A TrajectoryContainer between the two points
     */
    private static TrajectoryContainer buildSimpleCaseContainer(Pose2d initialPose2d, Pose2d endPose2d) {
        // Defines a waypoint in the middle of the start and ending position
        Translation2d midpoint = new Translation2d((initialPose2d.getX() + initialPose2d.getX()) / 2,
                (initialPose2d.getY() + initialPose2d.getY()) / 2);

        // Creates a new TrajectoryContainer object between the two poses and the
        // midpoint.
        return new TrajectoryContainer(initialPose2d, new ArrayList<Translation2d>(Arrays.asList(midpoint)), endPose2d);
    }

    private static TrajectoryContainer buildThroughTrenchContainer(Pose2d initialPose2d, Pose2d endPose2d) {
        ArrayList<Translation2d> interiorWaypoints = new ArrayList<>();

        FieldAreas initialArea = getFieldArea(initialPose2d);
        FieldAreas endingArea = getFieldArea(endPose2d);

        // We have to figure out which trench waypoints need to be included in the path.
        if (isCloseAllianceSide(getFieldArea(initialPose2d))) {
            // Route to trench close first
            // Need to first figure out how to get there.
            if (initialArea == FieldAreas.ALLIANCE_INITIATION) {
                interiorWaypoints.add(Waypoints.allianceInitiationLeft);
            } else if (initialArea == FieldAreas.BETWEEN_ALLIANCE_CLIMB_INITIATION) {
                interiorWaypoints.add(Waypoints.infrontOfTrenchAllianceSide);
            } else {
                // Something probably went wrong
            }
            interiorWaypoints.add(Waypoints.trenchClosePosition);
            interiorWaypoints.add(Waypoints.wofPosition);
            interiorWaypoints.add(Waypoints.trenchFarPosition);
        } else {
            // Route to trench far first.
            // Need to first figure out how to get there.

            if (initialArea == FieldAreas.ALLIANCE_INITIATION) {
                interiorWaypoints.add(Waypoints.allianceInitiationLeft);
            } else if (initialArea == FieldAreas.BETWEEN_ALLIANCE_CLIMB_INITIATION) {
                interiorWaypoints.add(Waypoints.infrontOfTrenchAllianceSide);
            } else {
                // Something probably went wrong
            }

            interiorWaypoints.add(Waypoints.trenchFarPosition);
            interiorWaypoints.add(Waypoints.wofPosition);
            interiorWaypoints.add(Waypoints.trenchClosePosition);
        }

        return new TrajectoryContainer(initialPose2d, interiorWaypoints, endPose2d);
    }
}
