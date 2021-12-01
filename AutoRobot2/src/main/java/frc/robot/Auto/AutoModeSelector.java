package frc.robot.Auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Auto.Modes.AutoModeBase;
import frc.robot.Auto.Modes.GoalToTrenchMode;

public class AutoModeSelector {

    // enum StartingPosition {
    // LEFT, MIDDLE, RIGHT
    // }

    enum DesiredAutoMode {
        DO_NOTHING, SHOOT_TWO, SHOOT_THREE, DRIVE_TO_GOAL_TO_TRENCH
    }

    private StartingPosition m_startPosition = null;
    private DesiredAutoMode m_desiredAutoMode = null;

    private SendableChooser<DesiredAutoMode> m_desiredAutoModeChooser;
    private SendableChooser<StartingPosition> m_startPositionChooser;

    public AutoModeSelector() {
        // Define Starting Position Chooser
        m_startPositionChooser = new SendableChooser<>();

        m_startPositionChooser.setDefaultOption("Left", StartingPosition.LEFT);
        m_startPositionChooser.addOption("Middle", StartingPosition.MIDDLE);
        m_startPositionChooser.addOption("Right", StartingPosition.RIGHT);

        SmartDashboard.putData("Starting Position", m_startPositionChooser);

        // Define Auto Mode Chooser
        m_desiredAutoModeChooser = new SendableChooser<>();
        m_desiredAutoModeChooser.setDefaultOption("Do Nothing", DesiredAutoMode.DO_NOTHING);
        m_desiredAutoModeChooser.addOption("Shoot Two", DesiredAutoMode.SHOOT_TWO);
        m_desiredAutoModeChooser.addOption("Shoot Three", DesiredAutoMode.SHOOT_THREE);
    }

    /**
     * Updates values from smart dashboard
     */
    public void update() {
        DesiredAutoMode desiredMode = m_desiredAutoModeChooser.getSelected();
        StartingPosition startingPosition = m_startPositionChooser.getSelected();

        if (m_startPosition != startingPosition || m_desiredAutoMode != desiredMode) {
            m_startPosition = startingPosition;
            m_desiredAutoMode = desiredMode;
        }
    }

    /**
     * Switch statement determines which auto mode is used. Will need to pass
     * starting position
     * 
     * @return the AutoMode to be executed
     */
    public AutoModeBase getAutoMode() {
        switch (this.m_desiredAutoMode) {
        case DO_NOTHING:
            // return new AutoModeBase();
            return null;
        case DRIVE_TO_GOAL_TO_TRENCH:
            return new GoalToTrenchMode(m_startPosition);
        case SHOOT_THREE:
            // return new AutoModeBase();
            return null;
        default:
            // return new AutoModeBase();
            return null;
        }
    }
}
