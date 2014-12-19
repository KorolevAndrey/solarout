package com.solarout.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.solarout.engine.*;
import com.solarout.graphics.actors.PlanetActor;

import java.util.ArrayList;
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

        Star star = new Star((float) (6.955F * Math.pow(10, 4)), (float) (1.98855F * Math.pow(10, 26)));
        float solarRadius = (float) (2 * (Math.pow(10, 8)));

        solarSystem = new UniStarSystem(solarRadius, star, 100F);
        Planet planetData = new Planet((float) (6.7F * Math.pow(10, 2)), (float) (5.9 * Math.pow(10, 20)));
        solarSystem.addStellarBody(
               planetData,
                new Vector3((float) (1.49F * Math.pow(10, 7)), 0, 0),
                new Velocity(new Vector3(0, 1, 0.01F), (float) (3.5F * Math.pow(10, 1))), UniStarSystem.RelativeObject.RELATIVE_TO_STAR);


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
        planetStar.setX(star.getPosition().x/meterPerPixel);
        planetStar.setY(star.getPosition().y/meterPerPixel);
        planets.put("star", planetStar);
        addActor(planetStar);

        ((OrthographicCamera)getCamera()).position.x = star.getPosition().x/meterPerPixel;
        ((OrthographicCamera)getCamera()).position.y = star.getPosition().y/meterPerPixel;

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
            for(int i = 0; i < 1000; i++) {
                solarSystem.tick();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
