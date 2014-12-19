package com.solarout.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector3;
import com.solarout.engine.*;
import com.solarout.graphics.actors.Planet3D;
import com.solarout.graphics.actors.PlanetActor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by CyberJoe on 12/19/2014.
 */
public class Viewer3D {

    private PerspectiveCamera cam;
    private ModelBatch modelBatch;

    private HashMap<String, Planet3D> planets = new HashMap<String, Planet3D>();

    public Environment environment;

    public CameraInputController camController;

    private UniStarSystem solarSystem;

    private float meterPerPixel = 200000000;
    private float mpp2 = 1;

    private ShapeRenderer shapeRenderer;

    private Vector3[] trajectoryPoints;
    private int tpIndex = 0;
    private Planet3D followingBody;

    public Viewer3D() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

        shapeRenderer = new ShapeRenderer();

        initWorld();
    }

    private void initWorld() {
        Star star = new Star((float) (6.955F * Math.pow(10, 4)), (float) (1.98855F * Math.pow(10, 26)));
        float solarRadius = (float) (2 * (Math.pow(10, 8)));
        solarSystem = new UniStarSystem(solarRadius, star, 40F);
        solarSystem.addStellarBody(
                new Planet((float) (3.5F * Math.pow(10, 2)), (float) (5.9 * Math.pow(10, 20))),
                new Vector3((float) (1.49F * Math.pow(10, 9)), 0, 0),
                new Velocity(new Vector3(0, 1, 0.F), (float) (4.7189F * Math.pow(10, 2))), UniStarSystem.RelativeObject.RELATIVE_TO_STAR);


        Iterator it = solarSystem.getStellarBodies().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String bodyName = (String) pairs.getKey();
            SphericStellarBody solarBody = (SphericStellarBody) pairs.getValue();

            Planet3D planet = new Planet3D(new Texture(Gdx.files.internal("p1.jpg")), 0.3f);
            planets.put(bodyName, planet);
            setCoordinateToObject(solarBody, planet);

            setTrajectryFollower(planet);
        }

        Planet3D starPlanet = new Planet3D(new Texture(Gdx.files.internal("texture_sun.jpg")), 2f);
        planets.put("star", starPlanet);
        setCoordinateToObject(star, starPlanet);

        pointCameraTo(star);
    }

    private void setTrajectryFollower(Planet3D body) {
        trajectoryPoints = new Vector3[200];
        followingBody = body;
        tpIndex = 0;
        //CatmullRomSpline<Vector3> myCatmull = new CatmullRomSpline<Vector3>(trajectoryPoints, true);
    }

    private void setCoordinateToObject(SphericStellarBody body, Planet3D planet) {
        Vector3 vec = new Vector3(body.getPosition().x / meterPerPixel/mpp2, body.getPosition().y / meterPerPixel/mpp2, body.getPosition().z / meterPerPixel/mpp2);

//        System.out.println(vec.y);
        planet.setPosition(vec.x, vec.y, vec.z);
    }

    private void pointCameraTo(SphericStellarBody body) {
        Vector3 vec = new Vector3(body.getPosition().x / meterPerPixel/mpp2, body.getPosition().y / meterPerPixel/mpp2, body.getPosition().z / meterPerPixel/mpp2);
        cam.position.set(vec.x, vec.y+10, vec.z+10);
        cam.lookAt(vec.x, vec.y, vec.z);
        cam.update();
    }

    public void draw() {
        modelBatch.begin(cam);

        for (String key : planets.keySet()) {
            Planet3D planet = planets.get(key);
            planet.draw(modelBatch, environment);
        }

        modelBatch.end();

        if(followingBody != null) {
            for(int i = 0; i < trajectoryPoints.length-1; i++) {
                if(trajectoryPoints[i+1] == null) break;

                shapeRenderer.setProjectionMatrix(cam.combined);

                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(1, 1, 0, 1);
                shapeRenderer.line(trajectoryPoints[i], trajectoryPoints[i+1]);
                shapeRenderer.end();
            }
        }

    }

    public void act(float delta) {
        camController.update();
        //cam.lookAt(0,0,0);
        //cam.update();

        Iterator it = solarSystem.getStellarBodies().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String bodyName = (String) pairs.getKey();
            SphericStellarBody solarBody = (SphericStellarBody) pairs.getValue();

            setCoordinateToObject(solarBody, planets.get(bodyName));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            try {
                for (int i = 0; i < 2000; i++) {
                    solarSystem.tick();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(followingBody != null) {
                if(tpIndex >= trajectoryPoints.length) {
                    tpIndex = trajectoryPoints.length-1;
                    for(int i = 0; i < trajectoryPoints.length-1; i++) {
                        trajectoryPoints[i] = trajectoryPoints[i+1];
                    }
                    trajectoryPoints[tpIndex] = new Vector3(followingBody.getPosition());
                } else {
                    trajectoryPoints[tpIndex++] = new Vector3(followingBody.getPosition());
                }

            }
        }

    }
}
