package com.solarout.engine;

import com.badlogic.gdx.math.Vector3;

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
    private float radius;

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
    private float tickLength;

    public float G = 6.67384F / 100000000000F;

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
    public UniStarSystem(float radius, Star star, float tickLength) {
        if (star.getRadius() >= radius) {
            throw new InvalidParameterException("Star radius should be less than system radius");
        }

        this.setTickLength(tickLength);

        this.star = star;
        this.radius = radius;

        this.stellarBodies = new HashMap<String, SphericStellarBody>();
        //center our star
        star.setPosition(new Vector3((float) radius, (float) radius, (float) radius));
    }

    public void addStellarBody(SphericStellarBody stellarBody, Vector3 position, Velocity velocity, SphericStellarBody relativeStellarBody) {
        if (stellarBody.getRadius() > relativeStellarBody.getRadius()) {
            throw new InvalidParameterException("Your stellarBody can not be bigger than your star");
        }

        if (stellarBody.getRadius() + relativeStellarBody.getRadius() >= this.radius) {
            throw new InvalidParameterException("Your stellarBody radius + star raius should be less than system radius, first please increase system radius");
        }

//        switch (relativeObject) {
//            case RELATIVE_TO_STAR:
//                float awayFromStarCenter = position.len();
//                if (awayFromStarCenter <= star.getRadius()) {
//                    throw new InvalidParameterException("Your stellarBody position is partly inside your star, it should be at least little bit away :)");
//                }


                position.add(relativeStellarBody.getPosition());

                stellarBody.setPosition(position);

                stellarBody.setVelocity(velocity);

                String planetName = stellarBody.getName();
                if (stellarBodies.containsKey(planetName)) {
                    throw new InvalidParameterException("Planet by this name already exist in your system, please change name of stellarBody first");
                }
                stellarBodies.put(planetName, stellarBody);
//                break;
//        }
    }

    public synchronized void tick() throws Exception {
        float tickLength = this.getTickLength();

        HashMap<String, Acceleration> bodiesGravityAcceleration = new HashMap<String, Acceleration>();

        HashMap<String, SphericStellarBody> stellarBodies = this.getStellarBodies();

        //calculate each stellar body change without apply (Apply later in order all changes go synchronously)
        Iterator it = this.getStellarBodies().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String bodyName = (String) pairs.getKey();
            SphericStellarBody stellarBody = (SphericStellarBody) pairs.getValue();
            try {
                bodiesGravityAcceleration.put(bodyName, this.calculateGravityAccelerationForStellarBody(stellarBody));
            } catch (Exception e) {
                System.out.print("Error calculating gravity acceleration");
            }
        }

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

        Vector3 accelerationVelocityDirection = new Vector3();
        float accelerationVelocityScalar = 0;
        Vector3 dir = new Vector3();


        accelerationVelocityScalar = (float) (this.G * this.star.getMass() / Math.pow(affectedStellarBody.getPosition().dst(star.getPosition()), 2));
        dir.set(star.getPosition()).sub(affectedStellarBody.getPosition()).nor();
        accelerationVelocityDirection = new Vector3(dir).scl(accelerationVelocityScalar, accelerationVelocityScalar, accelerationVelocityScalar);

        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();

            //do not calculate gravitation with itself
            if (pairs.getKey().equals(affectedStellarBodyName)) {
                continue;
            }

            SphericStellarBody affectingStellarBody = (SphericStellarBody) pairs.getValue();
            accelerationVelocityScalar = (float) (this.G * affectingStellarBody.getMass() / Math.pow(affectedStellarBody.getPosition().dst(affectingStellarBody.getPosition()), 2));
            dir.set(affectingStellarBody.getPosition()).sub(affectedStellarBody.getPosition()).nor();

            Vector3 tmp = new Vector3(dir).scl(accelerationVelocityScalar, accelerationVelocityScalar, accelerationVelocityScalar);
            accelerationVelocityDirection.add(tmp);
        }

        accelerationVelocityScalar = accelerationVelocityDirection.len();

        Acceleration gravityAcceleration = new Acceleration(accelerationVelocityDirection, accelerationVelocityScalar);
        return gravityAcceleration;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
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
    public float getTickLength() {
        return tickLength;
    }

    /**
     * @param tickLength
     */
    public void setTickLength(float tickLength) {
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