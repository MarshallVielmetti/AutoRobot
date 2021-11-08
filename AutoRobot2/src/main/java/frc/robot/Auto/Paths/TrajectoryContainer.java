package frc.robot.Auto.Paths;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.Constants;

/**
 * Base class for storing all trajectory information Incorporates config, etc.
 */
public class TrajectoryContainer {
    private TrajectoryConfig m_trajectoryConfig;
    private double m_maxVelocity;
    private double m_maxAcceleration;
    private Trajectory m_Trajectory;

    // Would also incorporate any type of contraints to add - TrajectoryConstraint

    public TrajectoryContainer(Pose2d initialPose, ArrayList<Translation2d> interiorWaypoints, Pose2d endingPose,
            double maxVelocity, double maxAcceleration) {
        this.m_maxVelocity = maxVelocity;
        this.m_maxAcceleration = maxAcceleration;

        this.m_trajectoryConfig = new TrajectoryConfig(m_maxVelocity, m_maxAcceleration);

        this.m_Trajectory = TrajectoryGenerator.generateTrajectory(initialPose, interiorWaypoints, endingPose,
                this.m_trajectoryConfig);
    }

    public TrajectoryContainer(Pose2d initialPose, ArrayList<Translation2d> interiorWaypoints, Pose2d endingPose) {
        this(initialPose, interiorWaypoints, endingPose, Constants.maxAutoVelocity, Constants.maxAutoAcceleration);
    }

    public Trajectory getTrajectory() {
        return this.m_Trajectory;
    }

}
