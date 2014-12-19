package com.solarout.graphics.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.solarout.engine.*;

/**
 * Created by azakhary on 12/19/2014.
 */
public class PlanetActor extends Image {

    private ShapeRenderer shapeRenderer;

    private float radius;

    public PlanetActor(float radius) {
        super(new Texture(Gdx.files.internal("pl1.png")));
        this.radius = radius;
        init();
    }

    public PlanetActor(SphericStellarBody bodyDescriptor) {
        super(new Texture(Gdx.files.internal("pl1.png")));
        radius = bodyDescriptor.getRadius()/10;
        init();
    }

    private void init() {
        setScale(0.3f, 0.3f);
        setOrigin(getWidth()/2, getHeight()/2);
    }

    public void setX(float x) {
        super.setX(x - getWidth()/2f);
    }

    public void setY(float y) {
        super.setY(y - getHeight()/2f);
    }

}
