package com.solarout.engine;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by aram on 12/15/2014.
 */
abstract public class SphericStellarBody extends MaterialPoint {

    private float radius;
    private float mass;
    private Velocity velocity;


    /**
     * @param radius   Object radius in meters
     * @param mass     Object mass in kilograms
     * @param position initial position of Object
     * @param velocity velocity of Object
     */
    public SphericStellarBody(Vector3 position, float radius, float mass, Velocity velocity) {
        super(position);

        this.radius = radius;
        this.mass = mass;
        this.velocity = velocity;
    }

    public SphericStellarBody(Vector3 position, float radius, float mass, Velocity velocity, String name) {
        super(position, name);

        this.radius = radius;
        this.mass = mass;
        this.velocity = velocity;
    }


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }


    public void move(float deltaTime) {
        Velocity bodyVelocity = this.getVelocity();
        Vector3 velocity = new Vector3(bodyVelocity.getVector()).
                scl(
                        bodyVelocity.getScalar(),
                        bodyVelocity.getScalar(),
                        bodyVelocity.getScalar());
        Vector3 movement = new Vector3();
        movement.set(velocity).scl(deltaTime, deltaTime, deltaTime);
        this.position.add(movement);
    }

    public void moveWIthAcceleration(float deltaTime, Acceleration acceleration) {

        Vector3 vNorm = new Vector3(velocity.getVector()).nor();
        Vector3 aNorm = new Vector3(acceleration.getVector()).nor();
        float cosAlpha = vNorm.dot(aNorm);

        float velocityChangeDueToAccelerationScalar = acceleration.getScalar();
        Vector3 accelerationNormalized = new Vector3(acceleration.getVector().scl(velocityChangeDueToAccelerationScalar, velocityChangeDueToAccelerationScalar, velocityChangeDueToAccelerationScalar));
        Vector3 velocityNormalized = new Vector3(velocity.getVector().scl(velocity.getScalar(), velocity.getScalar(), velocity.getScalar()));
        velocityNormalized.add(accelerationNormalized).nor();

        float velMagnitudeChange = cosAlpha * acceleration.getScalar();

        this.velocity.getVector().set(velocityNormalized);
        this.move(deltaTime);
        this.velocity.setScalar(this.velocity.getScalar() + velMagnitudeChange);
    }

}
