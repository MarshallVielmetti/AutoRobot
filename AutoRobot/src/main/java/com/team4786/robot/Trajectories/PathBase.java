package com.team4786.robot.Trajectories;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;

public interface PathBase {
    Path buildPath();

    boolean isReversed();
}
