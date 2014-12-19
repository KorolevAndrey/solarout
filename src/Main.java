import com.badlogic.gdx.math.Vector3;

public class Main {

    public static void main(String[] args) {
        Star star = new Star((float) (6.96F * Math.pow(10, 8)), (float) (1.98855F * Math.pow(10, 30)));
        float solarRadius = (float) (2 * (Math.pow(10, 12)));
        UniStarSystem solarSystem = new UniStarSystem(solarRadius, star);
        solarSystem.addStellarBody(
                new Planet((float) (6.7F * Math.pow(10, 6)), 5000),
                new Vector3((float) (1.8F * Math.pow(10, 11)), 0, 0),
                new Velocity(new Vector3(0, 1, 0.01F), (float) (3.5F * Math.pow(10, 4))), UniStarSystem.RelativeObject.RELATIVE_TO_STAR);

        try {
            solarSystem.tick();
            solarSystem.tick();
            solarSystem.print();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
