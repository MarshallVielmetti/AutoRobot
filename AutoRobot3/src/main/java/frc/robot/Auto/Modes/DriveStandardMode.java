package frc.robot.Auto.Modes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Auto.Paths.ExamplePath;
import frc.robot.commands.DrivePathCommand;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Standard drive a default path mode. For example only.
 * Steps (in order):
 * Runs an {@link DrivePathCommand} on {@link ExamplePath}
 */
public class DriveStandardMode extends SequentialCommandGroup {

    public DriveStandardMode(DriveSubsystem drive) {
        addCommands(new DrivePathCommand(new ExamplePath(), drive));
    }
}
