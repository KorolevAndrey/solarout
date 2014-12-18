import com.badlogic.gdx.math.Vector3;

import java.security.InvalidParameterException;
import java.util.HashMap;

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


    /**
     * Dots per meter
     */
    public static final int dotsPerMeter = 1;

    /**
     * Ticks per second
     */
    public static final int ticksPerSecond = 1000;

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

    public void tick() {

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