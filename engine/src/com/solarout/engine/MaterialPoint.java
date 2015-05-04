package com.solarout.engine;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by aram on 12/15/2014.
 */

abstract public class MaterialPoint {

    protected DoubleVector3 position;
    private Velocity velocity;

    protected MaterialPoint parent;
    private Acceleration acceleration;
    protected String name;

    /**
     * @param position position of the material point
     */

    public MaterialPoint(DoubleVector3 position, Velocity velocity, MaterialPoint parent) {
        this(position, velocity, parent, MaterialPoint.getRandomName());
    }

    public static String getRandomName() {
        SecureRandom random = new SecureRandom();
        return "Object-" + new BigInteger(30, random).toString(32);
    }

    public MaterialPoint(DoubleVector3 position, Velocity velocity, MaterialPoint parent, String name) {
        this.position = position;
        this.velocity = velocity;
        this.parent = parent;
        this.acceleration = new Acceleration(new DoubleVector3(0, 0, 0));
        this.name = name;
    }

    public DoubleVector3 getPosition() {
        return position;
    }

    public void setPosition(DoubleVector3 position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public double getVelocityScalar(boolean relativeToParent) {
        return this.getVelocityVector(relativeToParent).len();
    }

    public DoubleVector3 getVelocityVector(boolean relativeToParent) {
        DoubleVector3 velocity = this.getVelocity().getVector().cpy();
        if(relativeToParent && this.getParent() != null) {
            velocity.sub(this.getParent().getVelocity().getVector());
        }

        return velocity;
    }

    public DoubleVector3 getPositionVector(boolean relativeToParent) {
        DoubleVector3 position = this.getPosition().cpy();
        if(relativeToParent && this.getParent() != null) {
            position.sub(this.getParent().getPosition());
        }

        return position;
    }

    public double getDistanceFromParent() {
        if(this.getParent() != null) {
            return this.getPositionVector(true).len();
        }

        return 0;
    }

    public Acceleration getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Acceleration acceleration) {
        this.acceleration = acceleration;
    }

    public MaterialPoint getParent() {
        return parent;
    }

    public void setParent(MaterialPoint parent) {
        this.parent = parent;
    }
}
