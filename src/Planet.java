import com.badlogic.gdx.math.Vector3;

/**
 * Created by aram on 12/15/2014.
 */
public class Planet extends SphericObject{

    public Planet(float radius, double mass, Velocity velocity) {
        super(new Vector3(0, 0, 0), radius, mass, velocity);
    }

    public Planet(float radius, double mass, Velocity velocity, String name) {
        super(new Vector3(0, 0, 0), radius, mass, velocity, name);
    }

}
