package com.solarout.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.solarout.engine.*;
import com.solarout.graphics.actors.PlanetActor;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by azakhary on 12/19/2014.
 */
public class SolarStage extends Stage {

    private UniStarSystem solarSystem;

    private PlanetActor planet;

    public SolarStage() {


        Star star = new Star((float) (6.955F * Math.pow(10, 4)), (float) (1.98855F * Math.pow(10, 26)));
        float solarRadius = (float) (2 * (Math.pow(10, 8)));

        solarSystem = new UniStarSystem(solarRadius, star, 100F);
        Planet planetData = new Planet((float) (6.7F * Math.pow(10, 2)), (float) (5.9 * Math.pow(10, 20)));
        solarSystem.addStellarBody(
               planetData,
                new Vector3((float) (1.49F * Math.pow(10, 7)), 0, 0),
                new Velocity(new Vector3(0, 1, 0.01F), (float) (3.5F * Math.pow(10, 1))), UniStarSystem.RelativeObject.RELATIVE_TO_STAR);

        planet = new PlanetActor(planetData);
        planet.setX(getWidth()/2);
        planet.setY(getHeight() / 2);
        addActor(planet);
    }

    @Override
    public void act(float delta) {
        Iterator it = solarSystem.getStellarBodies().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String bodyName = (String) pairs.getKey();
            SphericStellarBody solarBody = (SphericStellarBody) pairs.getValue();

            System.out.println((int)(solarBody.getPosition().x/1000000));
            planet.setX(solarBody.getPosition().x/1000000);
            planet.setY(solarBody.getPosition().y/1000000);
        }

        try {
            solarSystem.tick();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
