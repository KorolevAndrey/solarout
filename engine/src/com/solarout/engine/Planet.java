package com.solarout.engine;

/**
 * Created by aram on 12/15/2014.
 */
public class Planet extends SphericStellarBody {

    public Planet(double radius, double mass, MaterialPoint parent) {
        super(new DoubleVector3(0, 0, 0), radius, mass, new Velocity(new DoubleVector3(0, 0, 0)), parent);
    }

//    public Planet(double radius, double mass, Velocity velocity) {
//        super(new MyVector3 (0, 0, 0), radius, mass, velocity);
//    }
//
    public Planet(double radius, double mass, MaterialPoint parent, String name) {
        super(new DoubleVector3(0, 0, 0), radius, mass, new Velocity(new DoubleVector3(0, 0, 0)), parent, name);
    }


}
