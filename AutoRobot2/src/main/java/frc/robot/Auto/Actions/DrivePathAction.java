package frc.robot.Auto.Actions;

import java.util.Set;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Auto.StartingPosition;
import frc.robot.Auto.Paths.TrajectoryBase;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Class that includes all of the RAMSETE controller logic to drive a
 * {@link TrajectoryBase}
 */
public class DrivePathAction extends ActionBase {
    private static DriveSubsystem m_drive = DriveSubsystem.getInstance();

    private static RamseteController m_ramseteController = new RamseteController(Constants.kRamseteB,
            Constants.kRamseteZeta);

    @Override
    public Set<Subsystem> getRequirements() {
        // TODO Auto-generated method stub
        return null;
    }

    public DrivePathAction(TrajectoryBase driveTrajectory, StartingPosition startingPosition) {
        RamseteCommand ramseteCommand = new RamseteCommand(driveTrajectory.getTrajectory(),
                DrivePathAction.m_drive::getCurrentPose2d, m_ramseteController,
                DrivePathAction.m_drive.getFeedForward(), DrivePathAction.m_drive.getDriveKinematics(),
                DrivePathAction.m_drive::getWheelSpeeds, new PIDController(Constants.kPDriveVel, 0, 0),
                new PIDController(Constants.kPDriveVel, 0, 0), m_drive::tankDriveVolts, m_drive);
    }

}
