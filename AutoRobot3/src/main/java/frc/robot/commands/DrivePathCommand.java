package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Auto.Paths.PathBase;
import frc.robot.subsystems.DriveSubsystem;

import static frc.robot.Constants.DriveConstants.*;

/**
 * Extends the RamseteCommand to make passing the information simpler
 * Don't have to redefine the {@link RamseteController} every time.
 * Allows for easier interfacing with {@link PathBase} system.
 * 
 * @see RamseteCommand
 */
public class DrivePathCommand extends RamseteCommand {

    /**
     * RamseteController that will be reused every time to avoid creating a lot of
     * the same ones.
     * Zeta and Beta are apparently robot-agnostic so probably don't need much
     * tuning
     * 
     * @see RamseteController
     */
    private static final RamseteController RAMSETE_CONTROLLER = new RamseteController(kRamseteB,
            kRamseteZeta);

    /**
     * Currently using the same PID controller for both the left and right side
     * Might make sense at a later date to shift to two different ones? depends on
     * how similar they are
     * 
     * @see {PIDController}
     */
    private static final PIDController PID_CONTROLLER = new PIDController(kP, kI, kD);

    /**
     * Passes all of the RamseteCommand requirements to the super class
     * Makes reusing this code MUCH simpler.
     * Primarily for use in complex, compound commands such as autonomous modes
     * TODO - Add breakdown of arguments passed to super.
     * 
     * @param driveSubystem
     */
    public DrivePathCommand(PathBase trajectory, DriveSubsystem driveSubystem) {
        super(
                trajectory.getTrajectory(),
                driveSubystem::getCurrentPose2d,
                DrivePathCommand.RAMSETE_CONTROLLER,
                driveSubystem.getFeedForward(),
                driveSubystem.getDriveKinematics(),
                driveSubystem::getWheelSpeeds,
                new PIDController(kP, kI, kD),
                new PIDController(kP, kI, kD),
                driveSubystem::tankDriveVolts,
                driveSubystem);
        /**
         * Item by item breakdown of arguments
         * Trajectory
         */
    }
}
