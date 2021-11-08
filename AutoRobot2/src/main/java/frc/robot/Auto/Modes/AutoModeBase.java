package frc.robot.Auto.Modes;

import java.util.ArrayList;

// import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Auto.StartingPosition;
import frc.robot.Auto.Actions.ActionBase;

public abstract class AutoModeBase extends SequentialCommandGroup {
    private ArrayList<ActionBase> m_orderedActions;
    public StartingPosition m_startingPosition;

    public AutoModeBase(StartingPosition startingPosition) {

    }

}
