package frc.robot.Auto.Modes;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
// import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Auto.StartingPosition;
import frc.robot.Auto.Paths.Waypoints;
import frc.robot.subsystems.DriveSubsystem;

public abstract class AutoModeBase extends SequentialCommandGroup {
    public StartingPosition m_startingPosition;

    public AutoModeBase(StartingPosition startingPosition) {
        this.m_startingPosition = startingPosition;
        Pose2d startPose = new Pose2d(Waypoints.getStartPositionFromEnum(startingPosition), new Rotation2d(0));

        DriveSubsystem.getInstance().setOdometry(startPose); // Illegal dependency injection

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

}
