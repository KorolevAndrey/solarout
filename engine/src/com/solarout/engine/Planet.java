package com.solarout.engine;

import com.badlogic.gdx.math.Vector3 ;

/**
 * Created by aram on 12/15/2014.
 */
public class Planet extends SphericStellarBody {

    public Planet(double radius, double mass) {
        super(new MyVector3 (0, 0, 0), radius, mass, new Velocity(new MyVector3 (0, 0, 0)));
    }

//    public Planet(double radius, double mass, Velocity velocity) {
//        super(new MyVector3 (0, 0, 0), radius, mass, velocity);
//    }
//
    public Planet(double radius, double mass, String name) {
        super(new MyVector3 (0, 0, 0), radius, mass, new Velocity(new MyVector3 (0, 0, 0)), name);
    }


}
