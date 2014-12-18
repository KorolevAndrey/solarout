import com.badlogic.gdx.math.Vector3;

public class Main {

    public static void main(String[] args) {
        Star star = new Star(100000, 10000);
        UniStarSystem solarSystem = new UniStarSystem(100000000, star);
        solarSystem.addStellarBody(
                new Planet(5000, 5000),
                new Vector3(300000, 300000, 300000),
                new Velocity(new Vector3(1, 1, 1), 100), UniStarSystem.RelativeObject.RELATIVE_TO_STAR);
    }
}
