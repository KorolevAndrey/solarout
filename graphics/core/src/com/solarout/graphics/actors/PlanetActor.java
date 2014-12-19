package com.solarout.graphics.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.solarout.engine.*;

/**
 * Created by azakhary on 12/19/2014.
 */
public class PlanetActor extends Actor {

    private ShapeRenderer shapeRenderer;

    private float radius;

    public PlanetActor(float radius) {
        this.radius = radius;
        init();
    }

    public PlanetActor(SphericStellarBody bodyDescriptor) {
        radius = bodyDescriptor.getRadius();
        init();
    }

    private void init() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(getX(), getY(), radius);
        shapeRenderer.end();

    }
}
