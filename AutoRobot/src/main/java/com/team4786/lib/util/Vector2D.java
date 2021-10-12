package com.team4786.lib.util;

import java.lang.Math;

/**
 * A container class to hold two related pairs of double values
 * Allow for easier calculations using vectors
 * In Julia this could've been a struct 
 * Because the language innately supports broadcasting operations but here we are
 */

public class Vector2D {
    private double x;
    private double y;
    
    /**
     * Specifying Constructor
     * @param x X Value
     * @param y Y Value
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Clone Constructor
     * @param guest Vector to create a clone of
     */
    public Vector2D(Vector2D guest) {
        this(guest.getX(), guest.getY());
    }

    /**
     * Creates a new vector from a 2 value array
     * @param arr Array of double, length 2
     */
    public Vector2D(double[] arr) {
        this(arr[0], arr[1]);
    }

    /**
     * Default Constructor
     */
    public Vector2D() {
        this(0, 0);
    }

    //Accessor / Mutator Methods
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    //Methods to facilitate inline vector calculations
    //These functions do NOT modify vectors - only return calculations based on them

    /**
     * Returns the result of scaling this vector by scale factor - useful for inline calculations
     * @param scaleFactor
     * @return Vector scaled by scaleFactor
     */
    public Vector2D getScaled(double scaleFactor) {
        return new Vector2D(this.getX()*scaleFactor, this.getY()*scaleFactor);
    }

    /**
     * Returns a Vector2D raised elementwise to a certain power
     * @param power Power to raise vector to
     * @return the result of raising each component to power 
     */
    public Vector2D getPower(int power) {
        return new Vector2D(Math.pow(this.getX(), power), Math.pow(this.getY(), power));
    }

    public Vector2D getSubtract(Vector2D guest) {
        return new Vector2D(this.getX() - guest.getX(), this.getY() - guest.getY());
    }

    /**
     * Facilitates the addition of a large number of vectors
     * @param toAdd Array of vector length > 1 to be added elementwise
     * @return A new Vector2D storing the result
     */
    public static Vector2D addArray(Vector2D[] toAdd) {
        double sumX = 0;
        double sumY = 0;
        for (int i = 0; i < toAdd.length; i++) {
            sumX += toAdd[i].getX();
            sumY += toAdd[i].getY();
        }
        return new Vector2D(sumX, sumY);
    }
}
