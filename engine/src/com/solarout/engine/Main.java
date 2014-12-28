package com.solarout.engine;

public class Main {

    public static void main(String[] args) {
//        Star star = new Star((double) (6.96F * Math.pow(10, 8)), (double) (1.98855F * Math.pow(10, 30)));
//        double solarRadius = (double) (2 * (Math.pow(10, 12)));
//        UniStarSystem solarSystem = new UniStarSystem(solarRadius, star, 2000F);
//        solarSystem.addStellarBody(
//                new Planet((double) (6.7F * Math.pow(10, 6)), 5000),
//                new MyVector3 ((double) (1.49F * Math.pow(10, 11)), 0, 0),
//                n ew Velocity(new MyVector3 (0, 1, 0.01F), (double) (3.5F * Math.pow(10, 4))), UniStarSystem.RelativeObject.RELATIVE_TO_STAR);

        Star star = new Star(6.371E6, 5.972E24);
        UniStarSystem solarSystem = new UniStarSystem(1.496E7, star, 0.5F);

//        Planet earth = new Planet(6.371E6, 5.972E24, "earth");
//        solarSystem.addStellarBody(
//                earth,
//                new DoubleVector3(1.496E11, 0, 0),
//                new Velocity(new DoubleVector3(0, 3E4, 0)), star);

        Planet moon = new Planet(3.5E5, 7.34767309E22, null, "moon");
        solarSystem.addStellarBody(
                moon,
                new DoubleVector3 (3.8E8, 0, 0),
                new Velocity(new DoubleVector3(0, 0, 1.0233E3)), star);


        Planet iss = new Planet(5.5E4, 7.34767309E10, null, "iss");
        solarSystem.addStellarBody(
                iss,
                new DoubleVector3 (0, 8E6, 0),
                new Velocity(new DoubleVector3(0, 0, 1.0233E3)), moon);


//        Star star = new Star((double) (6.955F * Math.pow(10, 4)), (double) (1.98855F * Math.pow(10, 26)));
//        double solarRadius = (double) (2 * (Math.pow(10, 8)));
//        UniStarSystem solarSystem = new UniStarSystem(solarRadius, star, 1F);
//
//        Planet earth = new Planet((double) (3.5F * Math.pow(10, 2)), (double) (5.9 * Math.pow(10, 20)), "Earth");
//        solarSystem.addStellarBody(
//                earth,
//                new MyVector3 ((double) (1.49F * Math.pow(10, 9)), 0, 0),
//                new Velocity(new MyVector3 (0, (double) (1.5F * Math.pow(10, 3)), 0), (double) (2.0F * Math.pow(10, 3))), star);

//        solarSystem.addStellarBody(
//                new Planet((double) (1F * Math.pow(10, 2)), (double) (7.3477 * Math.pow(10, 18)), "Moon"),
//                new MyVector3 (0, 0, (double) (4F * Math.pow(10, 6))),
//                new Velocity(new MyVector3 (0, 0, (double) (0.1F * Math.pow(10, 1))), (double) (0.1F * Math.pow(10, 2))), earth);



//        solarSystem.addStellarBody(
//                new Planet((double) (0.3F * Math.pow(10, 2)), (double) (7.3477 * Math.pow(10, 18)), "Moon"),
//                new MyVector3 (0, 0, (double) (4F * Math.pow(10, 7))),
//                new Velocity(new MyVector3 (0, 0, 0.0666F), (double) (0.3F * Math.pow(10, 2))), earth);

        try {


            solarSystem.print();
            for (int i = 1; i < 100; i++) {
                if (i % 200000 == 0) {
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
