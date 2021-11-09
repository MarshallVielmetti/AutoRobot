package frc.robot.Auto.Actions;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.Auto.Paths.TrajectoryBase;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Class that includes all of the RAMSETE controller logic to drive a
 * {@link TrajectoryBase}
 */
public class DrivePathCmd extends RamseteCommand {
    private static DriveSubsystem m_drive = DriveSubsystem.getInstance();

    private static RamseteController m_ramseteController = new RamseteController(Constants.kRamseteB,
            Constants.kRamseteZeta);

    public DrivePathCmd(TrajectoryBase driveTrajectory) {
        super(driveTrajectory.getTrajectory(), DrivePathCmd.m_drive::getCurrentPose2d, m_ramseteController,
                DrivePathCmd.m_drive.getFeedForward(), DrivePathCmd.m_drive.getDriveKinematics(),
                DrivePathCmd.m_drive::getWheelSpeeds, new PIDController(Constants.kPDriveVel, 0, 0),
                new PIDController(Constants.kPDriveVel, 0, 0), m_drive::tankDriveVolts, m_drive);
    }
}
