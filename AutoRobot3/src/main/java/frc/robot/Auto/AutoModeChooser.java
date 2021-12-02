package frc.robot.Auto;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * Class to allow for the building and selection of an autonomous mode.
 * Will get more complex as time goes on and the system gets more and more
 * robust.
 * Key idea is that it allows selecting an autonomous mode from SmartDashboard
 * while the robot is disabled before the match.
 */
public class AutoModeChooser {

    /**
     * Enum to hold all of the different auto modes. As complexity builds, will
     * dynamically build based on starting position.
     */
    enum DesiredAutoMode {
        EXAMPLE_MODE
    }

    private DesiredAutoMode m_desiredMode = null;

    private SendableChooser<DesiredAutoMode> m_autoChooser;

    public AutoModeChooser() {
        m_autoChooser = new SendableChooser<>();

    }

}
