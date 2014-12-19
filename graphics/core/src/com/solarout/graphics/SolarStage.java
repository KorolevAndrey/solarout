package com.solarout.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by azakhary on 12/19/2014.
 */
public class SolarStage extends Stage {

    private ShapeRenderer shapeRenderer;

    public SolarStage() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw() {
        super.draw();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(getWidth()/2, getHeight()/2, 20);
        shapeRenderer.end();

    }
}
