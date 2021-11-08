package frc.robot.Auto.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;

import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveKinematicsConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.MaxVelocityConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.TrajectoryConstraint;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Base class for storing all trajectory information Incorporates config, etc.
 * Reduces the amount of redundant code that goes into creating a trajectory.
 */
public class TrajectoryContainer {
        private TrajectoryConfig m_trajectoryConfig;
        private double m_maxVelocity;
        private double m_maxAcceleration;
        private Trajectory m_Trajectory;

        private CentripetalAccelerationConstraint m_centripetalConstraint;
        private DifferentialDriveKinematicsConstraint m_kinematicsConstraint;
        private DifferentialDriveVoltageConstraint m_voltageConstraint;

        /**
         * Specifying constructor for a Trajectory Container. This should most likely
         * never be used. Only use case would be limiting velocities over short runs.
         * 
         * @see CentripetalAccelerationConstraint
         * @see DifferentialDriveKinematicsConstraint
         * @see DifferentialDriveVoltageConstraint
         * @see TrajectoryConfig
         * @see Trajectory
         * 
         * @param initialPose             Pose2d which defines the robot's initial
         *                                position. Most often, this should be taken
         *                                from the position estimator
         * @param interiorWaypoints       ArrayList of Translation2ds which specify the
         *                                interior waypoints of the clamped cubic spline
         * @param endingPose              Pose2d which defines the robot's desired
         *                                ending position.
         * @param maxVelocity             Max velocity the robot is allowed to reach
         *                                over the trajectory
         * @param maxAcceleration         max acceleration the robot is allowed to apply
         *                                over the trajectory
         * @param centripetalAcceleration max centripetal acceleration the robot is
         *                                allowed to apply over the trajectory
         * @param maxAutoWheelVelocity    max velocity of an individual wheel well the
         *                                robot is allowed to reach over the trajectory
         */
        public TrajectoryContainer(Pose2d initialPose, ArrayList<Translation2d> interiorWaypoints, Pose2d endingPose,
                        double maxVelocity, double maxAcceleration, double centripetalAcceleration,
                        double maxAutoWheelVelocity) {

                this.m_maxVelocity = maxVelocity;
                this.m_maxAcceleration = maxAcceleration;

                // Getting drive characterizations for use in trajectory constraints
                DifferentialDriveKinematics driveKinematics = DriveSubsystem.getInstance().getDriveKinematics();
                SimpleMotorFeedforward feedforward = DriveSubsystem.getInstance().getFeedForward();

                // Define trajectory constraints
                m_centripetalConstraint = new CentripetalAccelerationConstraint(centripetalAcceleration);
                m_kinematicsConstraint = new DifferentialDriveKinematicsConstraint(driveKinematics,
                                maxAutoWheelVelocity);
                m_voltageConstraint = new DifferentialDriveVoltageConstraint(feedforward, driveKinematics,
                                Constants.maxVoltage);

                // Initialize trajectory config and add constraints
                // If someone wants to change the chained addConstraint to a single
                // addConstraints with conversion to list please do so
                this.m_trajectoryConfig = new TrajectoryConfig(m_maxVelocity, m_maxAcceleration);
                this.m_trajectoryConfig.addConstraint(m_centripetalConstraint).addConstraint(m_kinematicsConstraint)
                                .addConstraint(m_voltageConstraint);

                this.m_Trajectory = TrajectoryGenerator.generateTrajectory(initialPose, interiorWaypoints, endingPose,
                                this.m_trajectoryConfig);
        }

        /**
         * This is the recommended constructor for initializing a TrajectoryContainer
         * This uses the default values specified in {@link Constants} to define the
         * trajectory configuration and constraints
         * 
         * @param initialPose       Pose2d which defines the robot's initial position.
         *                          Most often, this should be taken from the position
         *                          estimator
         * @param interiorWaypoints ArrayList of Translation2ds which specify the
         *                          interior waypoints of the clamped cubic spline
         * @param endingPose        Pose2d which defines the robot's desired ending
         *                          position.
         */
        public TrajectoryContainer(Pose2d initialPose, ArrayList<Translation2d> interiorWaypoints, Pose2d endingPose) {
                this(initialPose, interiorWaypoints, endingPose, Constants.maxAutoVelocity,
                                Constants.maxAutoAcceleration, Constants.maxCentripetalAcceleration,
                                Constants.maxAutoWheelVelocity);
        }

        public Trajectory getTrajectory() {
                return this.m_Trajectory;
        }

}
