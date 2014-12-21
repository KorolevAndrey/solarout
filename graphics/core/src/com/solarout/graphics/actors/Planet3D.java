package com.solarout.graphics.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g3d.*;
import com.solarout.engine.MyVector3;

/**
 * Created by CyberJoe on 12/19/2014.
 */
public class Planet3D {

    private MyVector3 position;

    private float radius;

    private Texture texture;

    private Model model;

    public ModelInstance instance;

    public Planet3D(Texture texture, float radius) {
        this.texture = texture;
        this.radius = radius;

        ModelBuilder modelBuilder = new ModelBuilder();

        Material material = new Material();
        material.set(TextureAttribute.createDiffuse(texture));

        model = modelBuilder.createSphere(radius, radius, radius, 50, 50,
                material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instance = new ModelInstance(model);

        instance.transform.setToTranslation(0, 0, 0);
    }

    public void draw(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(instance, environment);
    }

    public void setPosition(float x, float y, float z) {
        instance.transform.setToTranslation(x, y, z);
        position = new MyVector3 (x, y, z);
    }

    public MyVector3  getPosition() {
        return position;
    }
}
