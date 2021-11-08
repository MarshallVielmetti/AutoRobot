package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.Auto.StartingPosition;
import frc.robot.Auto.Actions.Waypoints;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveSubsystem extends SubsystemBase {

    public static DriveSubsystem m_driveSubsystemInstance;

    private CANSparkMax m_leftMotor1 = new CANSparkMax(RobotMap.kLeftMotor1Port, MotorType.kBrushless);
    private CANSparkMax m_leftMotor2 = new CANSparkMax(RobotMap.kLeftMotor2Port, MotorType.kBrushless);
    private CANSparkMax m_leftMotor3 = new CANSparkMax(RobotMap.kLeftMotor3Port, MotorType.kBrushless);

    private CANSparkMax m_rightMotor2 = new CANSparkMax(RobotMap.kRightMotor2Port, MotorType.kBrushless);
    private CANSparkMax m_rightMotor1 = new CANSparkMax(RobotMap.kRightMotor1Port, MotorType.kBrushless);
    private CANSparkMax m_rightMotor3 = new CANSparkMax(RobotMap.kRightMotor3Port, MotorType.kBrushless);

    // The motors on the left side of the drive.
    private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(m_leftMotor1, m_leftMotor2,
            m_leftMotor3);

    // The motors on the right side of the drive.
    // Make sure they do not need to be inverted.
    private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(m_rightMotor1, m_rightMotor2,
            m_rightMotor3);

    private final DifferentialDrive m_drive;
    private final DifferentialDriveKinematics m_driveKinematics;
    private final SimpleMotorFeedforward m_feedFoward;

    // Define shaft encoders - not sure if this will stand the test of time.
    private final Encoder m_leftShaftEncoder = new Encoder(RobotMap.kLeftShaftEncoderPort[0],
            RobotMap.kLeftShaftEncoderPort[1], RobotMap.kLeftShaftEncoderReversed);

    private final Encoder m_rightShaftEncoder = new Encoder(RobotMap.kRightShaftEncoderPort[0],
            RobotMap.kRightShaftEncoderPort[1], RobotMap.kRightShaftEncoderReversed);

    // The gyro sensor
    private final Gyro m_gyro = new ADXRS450_Gyro();

    // Odometry class for tracking robot pose
    private final DifferentialDriveOdometry m_odometry;

    public DriveSubsystem() {
        // Allows for the drivesubsystem to be accessed everywhere

        DriveSubsystem.m_driveSubsystemInstance = this;
        // Not sure which side needs to be inverted
        m_leftMotors.setInverted(true);

        m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
        m_driveKinematics = new DifferentialDriveKinematics(Constants.distanceBetweenWheels);
        m_feedFoward = new SimpleMotorFeedforward(Constants.kS, Constants.kV, Constants.kA);

        m_leftShaftEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);
        m_rightShaftEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);

        // resetEncoders();
        m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation2d());

    };

    @Override
    public void periodic() {
        // TODO
        m_odometry.update(m_gyro.getRotation2d(), m_leftShaftEncoder.getDistance(), m_rightShaftEncoder.getDistance());
    };

    @Override
    public void setDefaultCommand(Command defaultCommand) {

    };

    // public void resetEncoders() {
    // m_leftEncoder.reset();
    // m_rightEncoder.reset();
    // }

    // public void resetOdometry(Pose2d pose) {
    // resetEncoders();
    // m_odometry.resetPosition(pose, m_gyro.getRotation2d());
    // }

    /** Zeroes the heading of the robot. */
    public void zeroHeading() {
        m_gyro.reset();
    }

    /**
     * Sets robot odometry to match currentPose
     * 
     * @param currentPose pose2d containing desired gyro angle and position
     */
    public void setOdometry(Pose2d currentPose) {
        // note that this accepts a Pose2d
        // this method will allow for the robot position to be reset at various times
        // throughout the match
        // Such as after using autonomous homing or the like

        m_odometry.resetPosition(currentPose, currentPose.getRotation());
    }

    public DifferentialDriveKinematics getDriveKinematics() {
        return this.m_driveKinematics;
    }

    public SimpleMotorFeedforward getFeedForward() {
        return this.m_feedFoward;
    }

    /**
     * Returns the current wheel speeds of the robot.
     *
     * @return The current wheel speeds.
     */
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(m_leftShaftEncoder.getRate(), m_rightShaftEncoder.getRate());
    }

    /**
     * Returns the estimated Pose2D of the robot not sure if this actually includes
     * the gyro value... but it should
     * 
     * @return Pose2d
     */
    public Pose2d getCurrentPose2d() {
        return m_odometry.getPoseMeters();
    }

    public static DriveSubsystem getInstance() {
        return DriveSubsystem.m_driveSubsystemInstance;
    }

    /**
     * Controls the left and right sides of the drive directly with voltages.
     *
     * @param leftVolts  the commanded left output
     * @param rightVolts the commanded right output
     */
    public void tankDriveVolts(double leftVolts, double rightVolts) {
        m_leftMotors.setVoltage(leftVolts);
        m_rightMotors.setVoltage(rightVolts); // might need to be inverted
        m_drive.feed();
    }
}
