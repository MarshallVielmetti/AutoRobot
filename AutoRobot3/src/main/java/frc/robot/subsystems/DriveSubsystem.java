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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DriveConstants.*;

public class DriveSubsystem extends SubsystemBase {

    // Forbidden progrmaming technique
    // Used to prevent having to recalculate trajectoryconfigs every time
    private static DriveSubsystem driveInstance;

    // Define & init left motors
    private final CANSparkMax m_motorL1 = new CANSparkMax(kLeftMotorID1, MotorType.kBrushless);
    private final CANSparkMax m_motorL2 = new CANSparkMax(kLeftMotorID2, MotorType.kBrushless);
    private final CANSparkMax m_motorL3 = new CANSparkMax(kLeftMotorID2, MotorType.kBrushless);

    private final CANEncoder m_leftEncoder = m_motorL1.getEncoder();

    // Define & init right motors
    private final CANSparkMax m_motorR1 = new CANSparkMax(kRightMotorID1, MotorType.kBrushless);
    private final CANSparkMax m_motorR2 = new CANSparkMax(kRightMotorID2, MotorType.kBrushless);
    private final CANSparkMax m_motorR3 = new CANSparkMax(kRightMotorID3, MotorType.kBrushless);

    private final CANEncoder m_rightEncoder = m_motorR1.getEncoder();

    // Define & init speed controller groups for each side
    private final SpeedControllerGroup m_leftControllerGroup = new SpeedControllerGroup(m_motorL1, m_motorL2,
            m_motorL3);

    private final SpeedControllerGroup m_rightControllerGroup = new SpeedControllerGroup(m_motorR1, m_motorR2,
            m_motorR3);

    // Define & init gyro
    private final Gyro m_gyro = new ADXRS450_Gyro();

    // Define & Initialize Drive Characterizations
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftControllerGroup, m_rightControllerGroup);
    private final DifferentialDriveKinematics m_kinematics = new DifferentialDriveKinematics(kWheelBase);
    private final DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(new Rotation2d());

    private final SimpleMotorFeedforward m_feedForward = new SimpleMotorFeedforward(kS, kV);

    private final boolean debug = true;

    public DriveSubsystem() {

        driveInstance = this; // Again, do not reference this

        // Set motor and encoder inversions
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
     * Returns the SimpleMotorFeedforwards
     * Primarily for use in the RamseteController
     * 
     * @return SimpleMotorFeedforward object representing the drive motors
     */
    public SimpleMotorFeedforward getFeedForward() {
        return m_feedForward;
    }

    /**
     * Returns the DifferentialDriveKinematics
     * Primarily for use in the RamseteController
     * 
     * @return DifferentialDriveKinematics object representing the drivetrain
     */
    public DifferentialDriveKinematics getDriveKinematics() {
        return m_kinematics;
    }

    /**
     * Gets the wheel speeds of the differential drive
     * 
     * @return DifferentialDriveWheelSpeeds
     */
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(m_leftEncoder.getVelocity(), m_rightEncoder.getVelocity());
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

    public void setOdometry(Pose2d setPose) {
        m_odometry.resetPosition(setPose, setPose.getRotation());
    }

    /**
     * Controls the left and right sides of the drive directly with voltages.
     *
     * @param leftVolts  the commanded left output
     * @param rightVolts the commanded right output
     */
    public void tankDriveVolts(double leftVolts, double rightVolts) {
        m_leftControllerGroup.setVoltage(leftVolts);
        m_rightControllerGroup.setVoltage(rightVolts); // might need to be inverted
        m_drive.feed();
    }

    @Override
    public void periodic() {
        m_odometry.update(m_gyro.getRotation2d(), m_leftEncoder.getPosition(), m_rightEncoder.getPosition());

        if (debug) {
            doDebug();
        }
    }

    /**
     * Method to write a lot of information to SmartDashboard
     * Avoids cluttering the periodic method
     * Probably more that really needs to be added here but...
     */
    private void doDebug() {
        SmartDashboard.putNumber("Left Encoder Position", m_leftEncoder.getPosition());
        SmartDashboard.putNumber("Right Encoder Position", m_rightEncoder.getPosition());
        SmartDashboard.putNumber("Gyro Angle", m_gyro.getAngle());
    }

    /**
     * This method is only here for use in the over-designed autonomous setup.
     * Do not make any calls to this method outside of that - it is against the
     * programming paradigms that we are trying to follow and makes the code more
     * difficult to understand
     * 
     * @return the DriveSubsystem instance variable
     */
    public static DriveSubsystem getInstance() {
        return driveInstance;
    }
}
