package frc.robot.Auto.Paths;

import javax.swing.TransferHandler.TransferSupport;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.Auto.StartingPosition;

/**
 * Container class that holds "waypoints" (Translation2ds) useful for
 * constructing paths around the field
 * 
 * @see NamedPositions
 * @see SmartTrajectoryBuilder
 * @see TrajectoryContainer
 */
public class Waypoints {
    // Starting position
    public static final Translation2d leftStartingPosition = new Translation2d();
    public static final Translation2d rightStartingPosition = new Translation2d();
    public static final Translation2d middleStartingPosition = new Translation2d();

    // Waypoints for use in @see
    public static final Translation2d loadingPosition = new Translation2d();
    // To be clear this is the spot right infront of the target
    public static final Translation2d targetPosition = new Translation2d();
    public static final Translation2d infrontOfTargetPosition = new Translation2d();

    public static final Translation2d wofPosition = new Translation2d();
    public static final Translation2d trenchFarPosition = new Translation2d();
    public static final Translation2d trenchClosePosition = new Translation2d();
    public static final Translation2d scoringRightPosition = new Translation2d();
    public static final Translation2d scoringLeftPosition = new Translation2d();

    public static final Translation2d allianceInitiationLeft = new Translation2d();
    public static final Translation2d allianceInitiationMiddle = new Translation2d();
    public static final Translation2d allianceInitiationRight = new Translation2d();

    public static final Translation2d infrontOfTrenchAllianceSide = new Translation2d();

    public static Translation2d getStartPositionFromEnum(StartingPosition startPos) {
        switch (startPos) {
        case LEFT:
            return Waypoints.leftStartingPosition;
        case RIGHT:
            return Waypoints.rightStartingPosition;
        case MIDDLE:
            return Waypoints.middleStartingPosition;
        default:
            return null;
        }
    }

}
