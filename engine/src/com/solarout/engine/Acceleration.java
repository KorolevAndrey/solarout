package com.solarout.engine;

import com.badlogic.gdx.math.Vector3 ;

/**
 * Created by aram on 12/19/2014.
 */
public class Acceleration extends Velocity {

    public static double G = 6.67384F / 100000000000F;

    public static int c = 1;

    public Acceleration(MyVector3  vector) {
        super(vector);
    }

    public Velocity accelerate(Velocity velocity, double dateInterval) {
        MyVector3  accelerationVector = this.getVector().cpy().scl((double) (dateInterval));
        MyVector3  velocityVector = velocity.getVector().cpy();
        velocityVector.add(accelerationVector);
        return new Velocity(velocityVector);
    }

    public static MyVector3  calculateGravityAcceleration (SphericStellarBody affectingStellarBody, SphericStellarBody affectedStellarBody) {
        double accelerationScalar;

        c++;

        double GMass = (double) (G * affectingStellarBody.getMass());
        double distanceBetween2Objects = affectedStellarBody.getPosition().dst(affectingStellarBody.getPosition());
        accelerationScalar =  (double) (GMass / Math.pow(distanceBetween2Objects, 2));
        MyVector3  dir = new MyVector3 ();
        dir.set(affectingStellarBody.getPosition()).sub(affectedStellarBody.getPosition());
        MyVector3  accelerationVector = new MyVector3 (dir).nor().scl(accelerationScalar);

//        if(affectedStellarBody.getPosition().x < -1.38879834E9 && affectedStellarBody.getPosition().x > -1.38881114E9) {
        if(c == 2) {
            System.out.print(affectedStellarBody.getPosition().x + " " + affectedStellarBody.getPosition().y + " " + accelerationVector.x + " " + accelerationVector.y + " " + accelerationVector.z);
            System.out.print(System.getProperty("line.separator"));
        }
        return accelerationVector;
    }

}