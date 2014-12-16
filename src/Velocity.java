import com.badlogic.gdx.math.Vector3;

/**
 * Created by aram on 12/15/2014.
 */
public class Velocity {

    private Vector3 vector;

    private float scalar;

    public Velocity(Vector3 vector, float scalar) {
        this.vector = vector;
        this.scalar = scalar;
    }

    public Vector3 getVector() {
        return vector;
    }

    public void setVector(Vector3 vector) {
        this.vector = vector;
    }

    public float getScalar() {
        return scalar;
    }

    public void setScalar(float scalar) {
        this.scalar = scalar;
    }
}
