import com.badlogic.gdx.math.Vector3;

/**
 * Created by aram on 12/15/2014.
 */

public class Star extends SphericObject {

    public Star(float radius, double mass) {
        super(new Vector3(0, 0, 0), radius, mass, new Velocity(new Vector3(0, 0, 0), 0));
    }

    public Star(float radius, double mass, String name) {
        super(new Vector3(0, 0, 0), radius, mass, new Velocity(new Vector3(0, 0, 0), 0), name);
    }

    public Star(Vector3 position, float radius, double mass) {
        super(position, radius, mass, new Velocity(new Vector3(0, 0, 0), 0));
    }

    public Star(Vector3 position, float radius, double mass, String name) {
        super(position, radius, mass, new Velocity(new Vector3(0, 0, 0), 0), name);
    }

}
