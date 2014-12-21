package com.solarout.engine;

import com.badlogic.gdx.math.Vector3 ;

/**
 * Created by aram on 12/15/2014.
 */
public class Velocity {

    private MyVector3  vector;

    public Velocity(MyVector3  vector) {
        this.vector = vector;
    }

    public MyVector3  getVector() {
        return vector;
    }

    public void setVector(MyVector3  vector) {
        this.vector = vector;
    }

    public double getScalar() {
        return vector.len();
    }

}
