package com.team4786.lib.util;

//This is class container for a waypoint used for spline planning

public class Waypoint {
    private Vector2D position;
    private Vector2D velocity;

    public Waypoint(Vector2D position, Vector2D velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public Vector2D getVelocity() {
        return this.velocity;
    }

    public Vector2D getPosition() {
        return this.position;
    }
}
