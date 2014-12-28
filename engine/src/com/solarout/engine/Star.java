package com.solarout.engine;

/**
 * Created by aram on 12/15/2014.
 */

public class Star extends SphericStellarBody {

    public Star(double radius, double mass) {
        super(new DoubleVector3(0, 0, 0), radius, mass, new Velocity(new DoubleVector3(0, 0, 0)));
    }

    public Star(double radius, double mass, String name) {
        super(new DoubleVector3(0, 0, 0), radius, mass, new Velocity(new DoubleVector3(0, 0, 0)), name);
    }

    public Star(DoubleVector3 position, double radius, double mass) {
        super(position, radius, mass, new Velocity(new DoubleVector3(0, 0, 0)));
    }

    public Star(DoubleVector3 position, double radius, double mass, String name) {
        super(position, radius, mass, new Velocity(new DoubleVector3(0, 0, 0)), name);
    }

}
