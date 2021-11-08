package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.DriveConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveSubsystem implements Subsystem {

    private CANSparkMax m_leftMotor1 = new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless);
    private CANSparkMax m_leftMotor2 = new CANSparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushless);
    private CANSparkMax m_leftMotor3 = new CANSparkMax(DriveConstants.kLeftMotor3Port, MotorType.kBrushless);

    private CANSparkMax m_rightMotor2 = new CANSparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushless);
    private CANSparkMax m_rightMotor1 = new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless);
    private CANSparkMax m_rightMotor3 = new CANSparkMax(DriveConstants.kRightMotor3Port, MotorType.kBrushless);

    // The motors on the left side of the drive.
    private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(m_leftMotor1, m_leftMotor2,
            m_leftMotor3);

    // The motors on the right side of the drive.
    // Make sure they do not need to be inverted.
    private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(m_rightMotor1, m_rightMotor2,
            m_rightMotor3);

    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

    // The gyro sensor
    private final Gyro m_gyro = new ADXRS450_Gyro();

    // Odometry class for tracking robot pose
    private final DifferentialDriveOdometry m_odometry;

    public DriveSubsystem() {
        // Not sure which side needs to be inverted
        m_leftMotors.setInverted(true);
        resetEncoders();
        m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation2d());
    };

    @Override
    public void periodic() {
    };

    @Override
    public void setDefaultCommand(Command defaultCommand) {

    };

    public void resetEncoders() {
        m_leftEncoder.reset();
        m_rightEncoder.reset();
    }

    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        m_odometry.resetPosition(pose, m_gyro.getRotation2d());
    }

    /** Zeroes the heading of the robot. */
    public void zeroHeading() {
        m_gyro.reset();
    }

}
