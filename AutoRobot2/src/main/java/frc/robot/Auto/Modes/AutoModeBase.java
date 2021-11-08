package frc.robot.Auto.Modes;

import java.util.ArrayList;

// import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Auto.StartingPosition;
import frc.robot.Auto.Actions.ActionBase;

public abstract class AutoModeBase extends SequentialCommandGroup {
    public StartingPosition m_startingPosition;

    public AutoModeBase(StartingPosition startingPosition) {
        this.m_startingPosition = startingPosition;
        this.buildAutoMode();
    };

    @Override
    public void initialize() {
        // TODO
        // Could potentially do something here
        // Such as resetting all of the encoders
        // Otherwise handled in robotInit?
        // I feel like handling here would be beneficial because it would allow for the
        // position etc. to be set accurately.
    }

    abstract void buildAutoMode();

    protected StartingPosition getStartingPosition() {
        return this.m_startingPosition;
    }

}
