package frc.robot.Auto.Modes;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
// import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Auto.StartingPosition;
import frc.robot.Auto.Actions.ActionBase;
import frc.robot.Auto.Paths.Waypoints;
import frc.robot.subsystems.DriveSubsystem;

public abstract class AutoModeBase extends SequentialCommandGroup {
    public StartingPosition m_startingPosition;

    public AutoModeBase(StartingPosition startingPosition) {
        this.m_startingPosition = startingPosition;
        DriveSubsystem.getInstance().setOdometry(getInitialPose());

        this.buildAutoMode();
    };

    @Override
    public void initialize() {
        // TODO
    }

    abstract void buildAutoMode();

    protected StartingPosition getStartingPosition() {
        return this.m_startingPosition;
    }

    private Pose2d getInitialPose() {
        Translation2d initialPosition;
        switch (this.m_startingPosition) {
        case LEFT:
            initialPosition = Waypoints.leftStartingPosition;
            break;
        case MIDDLE:
            initialPosition = Waypoints.middleStartingPosition;
            break;
        case RIGHT:
            initialPosition = Waypoints.rightStartingPosition;
            break;
        default:
            initialPosition = Waypoints.leftStartingPosition; // so I guess by default, it starts here
        }
    }

}
