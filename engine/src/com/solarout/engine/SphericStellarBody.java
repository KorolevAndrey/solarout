package com.solarout.engine;

/**
 * Created by aram on 12/15/2014.
 */
abstract public class SphericStellarBody extends MaterialPoint {

    private double radius;
    private double mass;


    /**
     * @param radius   Object radius in meters
     * @param mass     Object mass in kilograms
     * @param position initial position of Object
     * @param velocity velocity of Object
     */
    public SphericStellarBody(DoubleVector3 position, double radius, double mass, Velocity velocity, MaterialPoint parent) {
        super(position, velocity, parent);

        this.radius = radius;
        this.mass = mass;
    }

    public SphericStellarBody(DoubleVector3 position, double radius, double mass, Velocity velocity, MaterialPoint parent, String name) {
        super(position, velocity, parent, name);

        this.radius = radius;
        this.mass = mass;
    }


    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }


    public void move(double deltaTime) {
        this.position.add(this.getVelocity().getVector().cpy().scl(deltaTime));
    }

    public void moveWIthAcceleration(double deltaTime, Acceleration acceleration) {
        Velocity acceleratedVelocity = acceleration.accelerate(this.getVelocity(), deltaTime);
        this.setVelocity(acceleratedVelocity);
        this.move(deltaTime);
    }

}
