package com.solarout.engine;

/**
 * Created by aram on 12/19/2014.
 */
public class Acceleration extends Velocity {

    public static double G = 6.67384F / 100000000000F;

    public static int c = 1;

    public Acceleration(DoubleVector3 vector) {
        super(vector);
    }

    public Velocity accelerate(Velocity velocity, double dateInterval) {
        DoubleVector3 accelerationVector = this.getVector().cpy().scl((double) (dateInterval));
        DoubleVector3 velocityVector = velocity.getVector().cpy();
        velocityVector.add(accelerationVector);
        return new Velocity(velocityVector);
    }

    public static DoubleVector3 calculateGravityAcceleration (SphericStellarBody affectingStellarBody, SphericStellarBody affectedStellarBody) {
        double accelerationScalar;

        c++;

        double GMass = (double) (G * affectingStellarBody.getMass());
        double distanceBetween2Objects = affectedStellarBody.getPosition().dst(affectingStellarBody.getPosition());
        accelerationScalar =  (double) (GMass / Math.pow(distanceBetween2Objects, 2));
        DoubleVector3 dir = new DoubleVector3();
        dir.set(affectingStellarBody.getPosition()).sub(affectedStellarBody.getPosition());
        DoubleVector3 accelerationVector = new DoubleVector3(dir).nor().scl(accelerationScalar);

//        if(affectedStellarBody.getPosition().x < -1.38879834E9 && affectedStellarBody.getPosition().x > -1.38881114E9) {
        if(c == 2) {
            System.out.print(affectedStellarBody.getPosition().x + " " + affectedStellarBody.getPosition().y + " " + accelerationVector.x + " " + accelerationVector.y + " " + accelerationVector.z);
            System.out.print(System.getProperty("line.separator"));
        }
        return accelerationVector;
    }

}