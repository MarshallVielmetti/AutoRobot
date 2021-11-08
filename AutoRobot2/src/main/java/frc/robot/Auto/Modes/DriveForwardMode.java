package frc.robot.Auto.Modes;

import frc.robot.Auto.StartingPosition;
import frc.robot.Auto.Actions.DrivePathAction;
import frc.robot.subsystems.DriveSubsystem;

public class DriveForwardMode extends AutoModeBase {
    public DriveForwardMode(StartingPosition startingPosition) {
        super(startingPosition);
        super.addRequirements(DriveSubsystem.getInstance());

    }

    @Override
    public void buildAutoMode() {
        addCommands(new DrivePathAction(super.getStartingPosition()));
        addCommands(new DrivePathAction(super.getStartingPosition()));
        addCommands(new DrivePathAction(super.getStartingPosition()));
    }

}
