import com.badlogic.gdx.math.Vector3;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by aram on 12/15/2014.
 *
 * Unistar system is system with one star and one or more planet(s).
 *
 *
 */
public class UniStarSystem {

    /**
     *  Radius of solar system (in km)
     */
    private float radius;

    /**
     * Star object
     */
    private Star star;

    /**
     * Planets
     */
    private HashMap <String, SphericStellarBody> stellarBodies;

    /**
     * tick size in seconds
     */
    private float tickLength;

    public static final float G = 6.67384F * (10^11);


    /**
     * Dots per meter
     */

    public static enum RelativeObject {RELATIVE_TO_STAR};


    /**
     *
     * @param radius radius of system in meters
     * @param star
     */
    public UniStarSystem(float radius, Star star) {
        if(star.getRadius() >= radius) {
            throw new InvalidParameterException("Star radius should be less than system radius");
        }

        this.star = star;
        this.radius = radius;

        this.stellarBodies = new HashMap <String, SphericStellarBody>();
        //center our star
        star.setPosition(new Vector3((float) radius, (float) radius, (float) radius));
    }

    public void addStellarBody(SphericStellarBody stellarBody, Vector3 position, Velocity velocity, RelativeObject relativeObject) {
        if(stellarBody.getRadius() > this.star.getRadius()) {
            throw new InvalidParameterException("Your stellarBody can not be bigger than your star");
        }

        if(stellarBody.getRadius() + star.getRadius() >= this.radius) {
            throw new InvalidParameterException("Your stellarBody radius + star raius should be less than system radius, first please increase system radius");
        }

        switch(relativeObject) {
            case RELATIVE_TO_STAR:
                float awayFromStarCenter = position.len();
                if(awayFromStarCenter <= star.getRadius()) {
                    throw new InvalidParameterException("Your stellarBody position is partly inside your star, it should be at least little bit away :)");
                }

                position.add((float) star.getRadius());

                stellarBody.setPosition(position);

                stellarBody.setVelocity(velocity);

                String planetName = stellarBody.getName();
                if(stellarBodies.containsKey(planetName)) {
                    throw new InvalidParameterException("Planet by this name already exist in your system, please change name of stellarBody first");
                }
                stellarBodies.put(planetName, stellarBody);
                break;
        }
    }

    public synchronized void tick()  throws Exception {
        float tickLength = this.getTickLength();

        HashMap<String, Velocity> bodiesVelocities = new HashMap<String, Velocity>();

        HashMap<String, SphericStellarBody> stellarBodies = this.getStellarBodies();

        //calculate each stellar body change
        Iterator it = this.getStellarBodies().entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            String bodyName = (String) pairs.getKey();
            try {
                bodiesVelocities.put(bodyName, this.calculateGravityAccelerationForStellarBody(bodyName));
            } catch(Exception e) {
                System.out.print("Error calculating gravity acceleration");
            }
        }

        it = bodiesVelocities.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry velocitiesPair = (Map.Entry)it.next();
            String bodyName = (String) velocitiesPair.getKey();
            Velocity bodyVelocity = (Velocity) velocitiesPair.getValue();

            SphericStellarBody stellarBody = stellarBodies.get(bodyName);
            if(stellarBody == null) {
                throw new Exception("Error");
            }

            stellarBody.moveBy(bodyVelocity, tickLength);
        }
    }

    protected Velocity calculateGravityAccelerationForStellarBody(String stellarBodyName) throws Exception {
        HashMap<String, SphericStellarBody> stellarBodies = this.getStellarBodies();
        SphericStellarBody stellarBody = stellarBodies.get(stellarBodyName);
        if(stellarBody == null) {
            throw new Exception("Invalid stellar body name");
        }

        Iterator it = this.getStellarBodies().entrySet().iterator();


        Vector3 velocity = new Vector3();
        float acceleration = 0;

        Vector3 dir = new Vector3();

        while(it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();

            //do not calculate gravitation with itself
            if(pairs.getKey().equals(stellarBodyName)) {
                continue;
            }

            SphericStellarBody body = (SphericStellarBody) pairs.getValue();

            acceleration = (float) (this.G * body.getMass() * stellarBody.getMass() /  Math.pow(stellarBody.getPosition().dist2(body.getPosition()), 2));


            dir.set(body.getPosition()).sub(stellarBody.getPosition()).nor();

            if(velocity.isZero()) {
                velocity = new Vector3(dir).scale(acceleration, acceleration, acceleration);
            } else {
                Vector3 tmp = new Vector3(dir).scale(acceleration, acceleration, acceleration);
                velocity.dot(tmp);
            }

        }

        Velocity gravityAcceleration = new Velocity(velocity, acceleration);
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
     *
     * @return Tick length in seconds
     */
    public float getTickLength() {
        return tickLength;
    }

    /**
     *
     * @param tickLength
     */
    public void setTickLength(float tickLength) {
        this.tickLength = tickLength;
    }
}