package com.solarout.engine;

import com.badlogic.gdx.math.Vector3 ;

/**
 * Created by aram on 12/15/2014.
 */
abstract public class SphericStellarBody extends MaterialPoint {

    private double radius;
    private double mass;
    private Velocity velocity;


    /**
     * @param radius   Object radius in meters
     * @param mass     Object mass in kilograms
     * @param position initial position of Object
     * @param velocity velocity of Object
     */
    public SphericStellarBody(MyVector3  position, double radius, double mass, Velocity velocity) {
        super(position);

        this.radius = radius;
        this.mass = mass;
        this.velocity = velocity;
    }

    public SphericStellarBody(MyVector3  position, double radius, double mass, Velocity velocity, String name) {
        super(position, name);

        this.radius = radius;
        this.mass = mass;
        this.velocity = velocity;
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

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
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
