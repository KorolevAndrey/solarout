import com.badlogic.gdx.math.Vector3;

/**
 * Created by aram on 12/15/2014.
 */

public class Star extends SphericStellarBody {

    public Star(float radius, float mass) {
        super(new Vector3(0, 0, 0), radius, mass, new Velocity(new Vector3(0, 0, 0), 0));
    }

    public Star(float radius, float mass, String name) {
        super(new Vector3(0, 0, 0), radius, mass, new Velocity(new Vector3(0, 0, 0), 0), name);
    }

    public Star(Vector3 position, float radius, float mass) {
        super(position, radius, mass, new Velocity(new Vector3(0, 0, 0), 0));
    }

    public Star(Vector3 position, float radius, float mass, String name) {
        super(position, radius, mass, new Velocity(new Vector3(0, 0, 0), 0), name);
    }

}
