package com.solarout.engine;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by aram on 12/15/2014.
 * <p/>
 * Unistar system is system with one star and one or more planet(s).
 */
public class UniStarSystem {

    /**
     * Radius of solar system (in km)
     */
    private double radius;

    /**
     * Star object
     */
    private Star star;

    /**
     * Planets
     */
    private HashMap<String, SphericStellarBody> stellarBodies;

    /**
     * tick size in seconds
     */
    private double tickLength;


    /**
     * Dots per meter
     */

    public static enum RelativeObject {
        RELATIVE_TO_STAR,
        RELATIVE_TO_PLANET
    }

    ;


    /**
     * @param radius radius of system in meters
     * @param star
     */
    public UniStarSystem(double radius, Star star, double tickLength) {
        if (star.getRadius() >= radius) {
            throw new InvalidParameterException("Star radius should be less than system radius");
        }

        this.setTickLength(tickLength);

        this.star = star;
        this.radius = radius;

        this.stellarBodies = new HashMap<String, SphericStellarBody>();
        //center our star
        star.setPosition(new DoubleVector3((double) radius, (double) radius, (double) radius));
    }

    public void addStellarBody(SphericStellarBody stellarBody, DoubleVector3 position, Velocity velocity, SphericStellarBody relativeStellarBody) {
        if (stellarBody.getRadius() > relativeStellarBody.getRadius()) {
            throw new InvalidParameterException("Your stellarBody can not be bigger than your star");
        }

        if (stellarBody.getRadius() + relativeStellarBody.getRadius() >= this.radius) {
            throw new InvalidParameterException("Your stellarBody radius + star raius should be less than system radius, first please increase system radius");
        }

        stellarBody.setParent(relativeStellarBody);

        position.add(relativeStellarBody.getPosition());

        stellarBody.setPosition(position);

        if(relativeStellarBody.getVelocity().getVector().isZero()) {
            stellarBody.setVelocity(velocity);
        } else {
            Velocity relativeBodyVelocity = relativeStellarBody.getVelocity();
            DoubleVector3 tmpRelativeBodyVelocity = new DoubleVector3(relativeBodyVelocity.getVector());

            DoubleVector3 tmpMainBodyVelocity = new DoubleVector3(velocity.getVector());
            tmpMainBodyVelocity.add(tmpRelativeBodyVelocity);
            velocity.setVector(tmpMainBodyVelocity);
            stellarBody.setVelocity(velocity);
        }

        String planetName = stellarBody.getName();
        if (stellarBodies.containsKey(planetName)) {
            throw new InvalidParameterException("Planet by this name already exist in your system, please change name of stellarBody first");
        }
        System.out.print(planetName);
        stellarBodies.put(planetName, stellarBody);
    }

    public synchronized void tick() throws Exception {
        double tickLength = this.getTickLength();

        HashMap<String, Acceleration> bodiesGravityAcceleration = new HashMap<String, Acceleration>();

        HashMap<String, SphericStellarBody> stellarBodies = this.getStellarBodies();

        //calculate each stellar body change without apply (Apply later in order all changes go synchronously)
        Iterator it = this.getStellarBodies().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String bodyName = (String) pairs.getKey();
            SphericStellarBody stellarBody = (SphericStellarBody) pairs.getValue();
            try {
                Acceleration bodyAcceleration = this.calculateGravityAccelerationForStellarBody(stellarBody);
                stellarBody.setAcceleration(bodyAcceleration);
                bodiesGravityAcceleration.put(bodyName, bodyAcceleration);
            } catch (Exception e) {
                System.out.print("Error calculating gravity acceleration");
            }
        }

        //@TODO change this to read from bodys acceleration property not hahsmap

        //apply all changes
        it = bodiesGravityAcceleration.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry bodiesGravityAccelerationPair = (Map.Entry) it.next();
            String bodyName = (String) bodiesGravityAccelerationPair.getKey();
            Acceleration gravityAcceleration = (Acceleration) bodiesGravityAccelerationPair.getValue();

            SphericStellarBody stellarBody = stellarBodies.get(bodyName);
            if (stellarBody == null) {
                throw new Exception("Error");
            }

            stellarBody.moveWIthAcceleration(tickLength, gravityAcceleration);
        }
    }

    protected Acceleration calculateGravityAccelerationForStellarBody(SphericStellarBody affectedStellarBody) throws Exception {
        String affectedStellarBodyName = affectedStellarBody.getName();
        if (affectedStellarBody == null) {
            throw new Exception("Invalid stellar body name");
        }


        Iterator it = this.getStellarBodies().entrySet().iterator();
        DoubleVector3 gravityAccelerationVector = Acceleration.calculateGravityAcceleration(star, affectedStellarBody);
        double maxAccelerating = gravityAccelerationVector.len();
        affectedStellarBody.setParent(star);
        DoubleVector3 tmpAcceleration;

        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            //do not calculate gravitation with itself
            if (pairs.getKey().equals(affectedStellarBodyName)) {
                continue;
            }
            SphericStellarBody affectingStellarBody = (SphericStellarBody) pairs.getValue();
            tmpAcceleration = Acceleration.calculateGravityAcceleration(affectingStellarBody, affectedStellarBody);

            if(tmpAcceleration.cpy().sub(affectingStellarBody.getAcceleration().getVector()).len() > maxAccelerating) {
                maxAccelerating = tmpAcceleration.len();
                affectedStellarBody.setParent(affectingStellarBody);
            }

            gravityAccelerationVector.add(tmpAcceleration);
        }

        Acceleration gravityAcceleration = new Acceleration(gravityAccelerationVector);
        return gravityAcceleration;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public HashMap<String, SphericStellarBody> getStellarBodies() {
        return stellarBodies;
    }

    public void setStellarBodies(HashMap<String, SphericStellarBody> stellarBodies) {
        this.stellarBodies = stellarBodies;
    }

    /**
     * @return Tick length in seconds
     */
    public double getTickLength() {
        return tickLength;
    }

    /**
     * @param tickLength
     */
    public void setTickLength(double tickLength) {
        this.tickLength = tickLength;
    }

    public void print() {
        Iterator it = this.getStellarBodies().entrySet().iterator();
        System.out.print("Star: Position: x(" + this.star.getPosition().x + "), y("+this.star.getPosition().y+"), z("+this.star.getPosition().z +")");
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String bodyName = (String) pairs.getKey();
            SphericStellarBody solarBody = (SphericStellarBody) pairs.getValue();

            System.out.print("Body: " + bodyName + "; Position: x(" + solarBody.getPosition().x + "), y(" + solarBody.getPosition().y + "), z(" + +solarBody.getPosition().z + ")");
            System.out.print(System.getProperty("line.separator"));
        }
    }

}