package com.solarout.engine;

/**
 * Created by aram on 12/15/2014.
 */
public class Velocity {

    private DoubleVector3 vector;

    public Velocity(DoubleVector3 vector) {
        this.vector = vector;
    }

    public DoubleVector3 getVector() {
        return this.vector;
    }

    public void setVector(DoubleVector3 vector) {
        this.vector = vector;
    }

    public double getScalar() {
        return vector.len();
    }

}
