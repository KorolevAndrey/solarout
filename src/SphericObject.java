import com.badlogic.gdx.math.Vector3;

/**
 * Created by aram on 12/15/2014.
 */
abstract public class SphericObject extends MaterialPoint {

    private float radius;
    private float mass;
    private Velocity velocity;


    /**
     * @param radius Object radius in meters
     * @param mass Object mass in kilograms
     * @param position initial position of Object
     * @param velocity velocity of Object
     */
    public SphericObject(Vector3 position, float radius, float mass, Velocity velocity) {
        super(position);

        this.radius = radius;
        this.mass = mass;
        this.velocity = velocity;
    }

    public SphericObject(Vector3 position, float radius, float mass, Velocity velocity, String name) {
        super(position, name);

        this.radius = radius;
        this.mass = mass;
        this.velocity = velocity;
    }


    /**
     *
     * Move the object by specified duration in seconds
     *
     * @param duration
     */
    public void move(float duration) {
        this.getVelocity().setVector(this.getVelocity().getVector()); //@TODO
    }


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
}
