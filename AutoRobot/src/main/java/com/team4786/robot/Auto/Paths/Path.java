package com.team4786.robot.Auto.Paths;

import java.util.ArrayList;
import java.util.Collections;

import edu.wpi.first.wpilibj.geometry.Pose2d;

public class Path {
    public ArrayList<Pose2d> pose2ds;

    public Path() {
        this.pose2ds = new ArrayList<Pose2d>();
    }

    public Path(ArrayList<Pose2d> other) {
        Collections.copy(pose2ds, other);
    }

    public void addPose(Pose2d newPose) {
        pose2ds.add(newPose);
    };

    public void addPose(Pose2d newPose, int index) {
        pose2ds.add(index, newPose);
    }

    public Pose2d removePose(int index) {
        return this.pose2ds.remove(index);
    }

    public Pose2d getPose(int index) {
        return this.pose2ds.get(index);
    }
    
    public ArrayList<Pose2d> getPoseList() {
        return this.pose2ds;
    }
}
