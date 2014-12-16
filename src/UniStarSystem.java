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
    private HashMap <String, Planet> planets;

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

        //center our star
        star.setPosition(new Vector3((float) radius, (float) radius, (float) radius));
    }

    public void addPlanet(Planet planet, Vector3 position, RelativeObject relativeObject) {
        if(radius > this.star.getRadius()) {
            throw new InvalidParameterException("Your planet can not be bigger than your star");
        }

        if(radius + star.getRadius() <= this.radius) {
            throw new InvalidParameterException("Your planet radius + star raius should be less than system radius, first please increase system radius");
        }

        switch(relativeObject) {
            case RELATIVE_TO_STAR:
                double awayFromStarCenter = position.len();
                if(awayFromStarCenter <= star.getRadius()) {
                    throw new InvalidParameterException("Your planet position is partly inside your star, it should be at least little bit away :)");
                }

                position.add((float) star.getRadius());

                planet.setPosition(position);
                String planetName = planet.getName();
                if(planets.containsKey(planetName)) {
                    throw new InvalidParameterException("Planet by this name already exist in your system, please change name of planet first");
                }
                planets.put(planetName, planet);
                break;
        }
    }

    public void tick() {

    }

}