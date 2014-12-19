package com.solarout.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.solarout.graphics.actors.PlanetActor;

/**
 * Created by azakhary on 12/19/2014.
 */
public class SolarStage extends Stage {

    private PlanetActor planet;

    public SolarStage() {
        planet = new PlanetActor(30);
        planet.setX(getWidth()/2);
        planet.setY(getHeight() / 2);
        addActor(planet);
    }

    @Override
    public void act(float delta) {

    }
}
