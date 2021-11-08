package frc.robot.Auto.Actions;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Auto.StartingPosition;

public class DrivePathAction extends ActionBase {
    @Override
    public Set<Subsystem> getRequirements() {
        // TODO Auto-generated method stub
        return null;
    }

    public DrivePathAction(StartingPosition startingPosition) {
        super(startingPosition);
    }

}
