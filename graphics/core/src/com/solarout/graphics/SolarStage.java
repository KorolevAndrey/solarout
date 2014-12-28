package com.solarout.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.solarout.engine.*;
import com.solarout.graphics.actors.PlanetActor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by azakhary on 12/19/2014.
 */
public class SolarStage extends Stage {

    private UniStarSystem solarSystem;

    private HashMap<String, PlanetActor> planets = new HashMap<String, PlanetActor>();

    private float meterPerPixel = 1000000;

    public SolarStage() {
        super();

        Star star = new Star(6.371E6, 5.972E24);
        solarSystem = new UniStarSystem(1.496E7, star, 0.5F);

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



        Iterator it = solarSystem.getStellarBodies().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String bodyName = (String) pairs.getKey();
            SphericStellarBody solarBody = (SphericStellarBody) pairs.getValue();

            PlanetActor planet = new PlanetActor(20);
            planets.put(bodyName, planet);
            addActor(planet);
        }


        PlanetActor planetStar = new PlanetActor(70);
        planetStar.setColor(Color.YELLOW);
        planetStar.setX((float) star.getPosition().x/meterPerPixel);
        planetStar.setY((float) star.getPosition().y/meterPerPixel);
        planets.put("star", planetStar);
        addActor(planetStar);

        ((OrthographicCamera)getCamera()).position.x = (float) star.getPosition().x/meterPerPixel;
        ((OrthographicCamera)getCamera()).position.y = (float) star.getPosition().y/meterPerPixel;

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Iterator it = solarSystem.getStellarBodies().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String bodyName = (String) pairs.getKey();
            SphericStellarBody solarBody = (SphericStellarBody) pairs.getValue();

            PlanetActor planet = planets.get(bodyName);
            planet.setX((int)(solarBody.getPosition().x/meterPerPixel));
            planet.setY((int)(solarBody.getPosition().y/meterPerPixel));

            //planet.setX(0);
            //planet.setY(0);
        }

        try {
            for(int i = 0; i < 500; i++) {
                solarSystem.tick();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
