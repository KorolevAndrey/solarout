package com.solarout.engine;

import com.badlogic.gdx.math.Vector3;

public class Main {

    public static void main(String[] args) {
//        Star star = new Star((float) (6.96F * Math.pow(10, 8)), (float) (1.98855F * Math.pow(10, 30)));
//        float solarRadius = (float) (2 * (Math.pow(10, 12)));
//        UniStarSystem solarSystem = new UniStarSystem(solarRadius, star, 2000F);
//        solarSystem.addStellarBody(
//                new Planet((float) (6.7F * Math.pow(10, 6)), 5000),
//                new Vector3((float) (1.49F * Math.pow(10, 11)), 0, 0),
//                new Velocity(new Vector3(0, 1, 0.01F), (float) (3.5F * Math.pow(10, 4))), UniStarSystem.RelativeObject.RELATIVE_TO_STAR);

        Star star = new Star((float) (6.955F * Math.pow(10, 4)), (float) (1.98855F * Math.pow(10, 26)));
        float solarRadius = (float) (2 * (Math.pow(10, 8)));
        UniStarSystem solarSystem = new UniStarSystem(solarRadius, star, 100F);
        solarSystem.addStellarBody(
                new Planet((float) (6.7F * Math.pow(10, 2)), (float) (5.9 * Math.pow(10, 20))),
                new Vector3((float) (1.49F * Math.pow(10, 7)), 0, 0),
                new Velocity(new Vector3(0, 1, 0.01F), (float) (3.5F * Math.pow(10, 1))), UniStarSystem.RelativeObject.RELATIVE_TO_STAR);

        try {
            solarSystem.print();
            for (int i = 1; i < 315360; i++) {
                if (i % 50000 == 0) {
                    solarSystem.print();
                }
                solarSystem.tick();
            }
            solarSystem.print();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
