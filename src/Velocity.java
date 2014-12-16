import com.badlogic.gdx.math.Vector3;

/**
 * Created by aram on 12/15/2014.
 */
public class Velocity {

    private Vector3 vector;

    private double scalar;

    public Velocity(Vector3 vector, double scalar) {
        this.vector = vector;
        this.scalar = scalar;
    }

    public Vector3 getVector() {
        return vector;
    }

    public void setVector(Vector3 vector) {
        this.vector = vector;
    }

    public double getScalar() {
        return scalar;
    }

    public void setScalar(double scalar) {
        this.scalar = scalar;
    }
}
