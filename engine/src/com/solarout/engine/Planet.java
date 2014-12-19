package com.solarout.engine;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by aram on 12/15/2014.
 */
public class Planet extends SphericStellarBody {

    public Planet(float radius, float mass) {
        super(new Vector3(0, 0, 0), radius, mass, new Velocity(new Vector3(0, 0, 0), 0));
    }

//    public Planet(float radius, float mass, Velocity velocity) {
//        super(new Vector3(0, 0, 0), radius, mass, velocity);
//    }
//
//    public Planet(float radius, float mass, Velocity velocity, String name) {
//        super(new Vector3(0, 0, 0), radius, mass, velocity, name);
//    }


}
