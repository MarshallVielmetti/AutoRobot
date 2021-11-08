package frc.robot.Auto.Paths;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.subsystems.DriveSubsystem;

public class DriveForwardTrajectory extends TrajectoryBase {
    private Pose2d initialPose;
    private ArrayList<Translation2d> interiorPoints;
    private Pose2d endingPose;

    /**
     * Probably redundant. Ideally the super will call the buildTrajectoryContainer
     * method defined below.
     */
    public DriveForwardTrajectory() {
        super();
    }

    @Override
    public TrajectoryContainer buildTrajectoryContainer() {
        this.initialPose = DriveSubsystem.getInstance().getCurrentPose2d(); // Note that even if this is the first
                                                                            // trajectory to run, the initial Pose2d is
                                                                            // defined by the AutoModeBase

        return new TrajectoryContainer(this.initialPose, this.interiorPoints, this.endingPose);
    }

}