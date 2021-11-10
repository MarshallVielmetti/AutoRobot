package frc.robot.Auto.Modes;

import frc.robot.Auto.StartingPosition;
import frc.robot.Auto.Actions.DrivePathCmd;
import frc.robot.Auto.Paths.GoaltoFarTrench;
import frc.robot.Auto.Paths.StartLinetoGoal;

/**
 * Drives path from start line (adaptive position) to Trench
 */
public class GoalToTrenchMode extends AutoModeBase {
    public GoalToTrenchMode(StartingPosition startPos) {
        super(startPos);
    }

    @Override
    protected void buildAutoMode() {
        // TODO Auto-generated method stub
        // Commands in order
        addCommands(new DrivePathCmd(new StartLinetoGoal(super.getStartingPosition())),
                new DrivePathCmd(new GoaltoFarTrench()));
    }
}
