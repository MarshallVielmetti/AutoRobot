package frc.robot.Auto.Actions;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Auto.StartingPosition;
import frc.robot.Auto.Paths.TrajectoryBase;

/**
 * Class that includes all of the RAMSETE controller logic to drive a
 * {@link TrajectoryBase}
 */
public class DrivePathAction extends ActionBase {
    @Override
    public Set<Subsystem> getRequirements() {
        // TODO Auto-generated method stub
        return null;
    }

    public DrivePathAction(TrajectoryBase driveTrajectory, StartingPosition startingPosition) {

    }

}
