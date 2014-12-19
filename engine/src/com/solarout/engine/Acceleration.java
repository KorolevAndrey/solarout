package com.solarout.engine;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by aram on 12/19/2014.
 */
public class Acceleration extends Velocity {

    public Acceleration(Vector3 vector, float scalar) {
        super(vector, scalar);
    }

    public Velocity getVelocityChange(float deltaTime) {
        return new Velocity(this.getVector(), deltaTime * this.getScalar());
    }
}
