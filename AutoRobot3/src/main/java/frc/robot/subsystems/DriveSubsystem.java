package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DriveConstants.*;

public class DriveSubsystem extends SubsystemBase {

    //Define & init left motors
    private final CANSparkMax m_motorL1 = new CANSparkMax(kLeftMotorID1, MotorType.kBrushless);
    private final CANSparkMax m_motorL2 = new CANSparkMax(kLeftMotorID2, MotorType.kBrushless);
    private final CANSparkMax m_motorL3 = new CANSparkMax(kLeftMotorID2, MotorType.kBrushless);

    private final CANEncoder m_leftEncoder = m_motorL1.getEncoder();

    //Define & init right motors
    private final CANSparkMax m_motorR1 = new CANSparkMax(kRightMotorID1, MotorType.kBrushless);
    private final CANSparkMax m_motorR2 = new CANSparkMax(kRightMotorID2, MotorType.kBrushless);
    private final CANSparkMax m_motorR3 = new CANSparkMax(kRightMotorID3, MotorType.kBrushless);

    private final CANEncoder m_rightEncoder = m_motorR1.getEncoder();

    //Define & init speed controller groups for each side
    private final SpeedControllerGroup m_leftControllerGroup = new SpeedControllerGroup(m_motorL1, m_motorL2, m_motorL3);

    private final SpeedControllerGroup m_rightControllerGroup = new SpeedControllerGroup(m_motorR1, m_motorR2, m_motorR3);

    //Define & init gyro
    private final Gyro m_gyro = new ADXRS450_Gyro();

    //Define & Initialize Drive Characterizations
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftControllerGroup, m_rightControllerGroup);
    private final DifferentialDriveKinematics m_kinematics = new DifferentialDriveKinematics(kWheelBase);
    private final DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(new Rotation2d());

    private final SimpleMotorFeedforward m_feedForward = new SimpleMotorFeedforward(kS, kV);

    public DriveSubsystem() {

        //Set motor and encoder inversions
        m_leftControllerGroup.setInverted(kLeftInverted);
        m_rightControllerGroup.setInverted(kRightInverted);

        m_leftEncoder.setInverted(kLeftEncoderInverted);
        m_rightEncoder.setInverted(kRightEncoderInverted);

        m_leftEncoder.setVelocityConversionFactor(kDriveVelocityConversionFactor);
        m_rightEncoder.setVelocityConversionFactor(kDriveVelocityConversionFactor);
    }

    /**
     * Reset all encoders - 
     * Gyro, CAN Quadrature Encoders
     */
    public void zeroAll() {
        m_gyro.reset();

        m_leftEncoder.setPosition(0);
        m_rightEncoder.setPosition(0);
    }

    /**
     * Returns arbitrary feedforward value to achieve a desired velocity
     * @param desiredVelocity - desired velocity
     * @return arbitrary feedforwards value
     */
    public double getFeedForward(double desiredVelocity) {
        return m_feedForward.calculate(desiredVelocity);
    }

    /**
     * Gets the wheel speeds of the differential drive
     * @return DifferentialDriveWheelSpeeds
     */
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(m_leftEncoder.getVelocity(), m_rightEncoder.getVelocity());
    }

    public Pose2d getPose2d() {
        return m_odometry.getPoseMeters();
    }







}
