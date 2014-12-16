import com.badlogic.gdx.math.Vector3;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by aram on 12/15/2014.
 */

abstract public class MaterialPoint {

    private Vector3 position;

    private String name;

    /**
     *
     * @param position position of the material point
     */

    public MaterialPoint(Vector3 position) {
        this.position = position;
        this.assignRandomName();
    }

    public void assignRandomName() {
        SecureRandom random = new SecureRandom();
        this.name = "Object-" + new BigInteger(30, random).toString(32);
    }

    public MaterialPoint(Vector3 position, String name) {
        this.position = position;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
