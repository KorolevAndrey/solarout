package com.solarout.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.MathUtils;
import com.solarout.engine.*;
import com.solarout.graphics.actors.Planet3D;
import com.solarout.graphics.actors.Trajectory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by CyberJoe on 12/19/2014.
 */
public class Viewer3D {

    UIStage uiStage;

    private PerspectiveCamera cam;
    private ModelBatch modelBatch;

    private HashMap<String, Planet3D> planets = new HashMap<String, Planet3D>();

    public Environment environment;

    public CameraInputController camController;

    private UniStarSystem solarSystem;

    private float meterPerPixel = 100000000f;
    private float mpp2 = 1;

    public ArrayList<Trajectory> trajectories = new ArrayList<Trajectory>();
    public ArrayList<Color> colorMap = new ArrayList<Color>();

    public Viewer3D(UIStage uiStage) {

        this.uiStage = uiStage;

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0f, 10000f, 0f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);

        colorMap.add(0, Color.BLUE);
        colorMap.add(1, Color.CYAN);
        colorMap.add(2, Color.GREEN);
        colorMap.add(3, Color.MAGENTA);
        colorMap.add(4, Color.NAVY);
        colorMap.add(5, Color.OLIVE);
        colorMap.add(6, Color.ORANGE);
        colorMap.add(7, Color.YELLOW);
        colorMap.add(8, Color.RED);


        initWorld();
    }

    private void initWorld() {
//        Star star = new Star((float) (6.371 * Math.pow(10, 6)), (float) (5.97219 * Math.pow(10, 24)));
//        float solarRadius = (float) (2 * (Math.pow(10, 8)));
//        solarSystem = new UniStarSystem(solarRadius, star, 8F);
//
//        Planet earth = new Planet(1737000F, (float) (7.34767309F * Math.pow(10, 22)), "Earth");
//        solarSystem.addStellarBody(
//                earth,
//                new MyVector3 ((float) (3.844F * Math.pow(10, 8)), 0, 0),
//                new Velocity(new MyVector3 (0, (float) (1.023F * Math.pow(10, 3)), 0), (float) (1.023F * Math.pow(10, 3))), star);
//        solarSystem.print();


//        Star star = new Star((float) (6.955F * Math.pow(10, 4)), (float) (5.972F * Math.pow(10, 24)));
//        float solarRadius = (float) (2 * (Math.pow(10, 8)));
//        solarSystem = new UniStarSystem(solarRadius, star, 5F);
//
//        Planet earth = new Planet((float) (3.5F * Math.pow(10, 2)), (float) (7.34767309F * Math.pow(10, 22)));
//        solarSystem.addStellarBody(
//                earth,
//                new MyVector3 ((float) (3.8F * Math.pow(10, 8)), 0, 0),
//                new Velocity(new MyVector3(0, (float) (1.023F * Math.pow(10, 3)), 0)), star);

//        Planet moon = new Planet((float) (.5F * Math.pow(10, 2)), (float) (1F * Math.pow(10, 4)));
//        solarSystem.addStellarBody(
//                moon,
//                new MyVector3 (0, (float) (6F * Math.pow(10, 5)), 0),
//                new Velocity(new MyVector3 (0, (float) (.740F * Math.pow(10, 2)), 0)), earth);

//        solarSystem.addStellarBody(
//                new Planet((float) (0.3F * Math.pow(10, 2)), (float) (7.3477 * Math.pow(10, 18)), "Moon"),
//                new MyVector3 (0, 0, (float) (4F * Math.pow(10, 7))),
//                new Velocity(new MyVector3 (0, 0, 0.0666F), (float) (0.4F * Math.pow(10, 2))), earth);


//        Star star = new Star(6.958E8, 1.989E30);
        Star star = new Star(6.371E6, 5.972E24);
        solarSystem = new UniStarSystem(1.496E7, star, 0.1F);

//        Planet earth = new Planet(6.371E6, 5.972E24, "earth");
//        solarSystem.addStellarBody(
//                earth,
//                new DoubleVector3(1.496E11, 0, 0),
//                new Velocity(new DoubleVector3(0, 3E4, 0)), star);

        Planet moon = new Planet(3.5E5, 7.34767309E22, "moon");
        solarSystem.addStellarBody(
                moon,
                new DoubleVector3 (3.8E8, 0, 0),
                new Velocity(new DoubleVector3(0, 0, 1.0233E3)), star);


        Planet iss = new Planet(5.5E4, 7.34767309E10, "iss");
        solarSystem.addStellarBody(
                iss,
                new DoubleVector3 (0, 3E6, 0),
                new Velocity(new DoubleVector3(0, 0, 1.0233E3)), moon);

        Iterator it = solarSystem.getStellarBodies().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String bodyName = (String) pairs.getKey();
            SphericStellarBody solarBody = (SphericStellarBody) pairs.getValue();

            Planet3D planet = new Planet3D(new Texture(Gdx.files.internal("p1.jpg")), 0.05f);
            planets.put(bodyName, planet);
            setCoordinateToObject(solarBody, planet);

            int clr = MathUtils.random(0, 8);
            Color color = colorMap.get(clr);
            setTrajectryFollower(planet, color);
        }

        Planet3D starPlanet = new Planet3D(new Texture(Gdx.files.internal("texture_sun.jpg")), 2f);
        planets.put("star", starPlanet);
        setCoordinateToObject(star, starPlanet);

        pointCameraTo(star);

        uiStage.setSystem(solarSystem);
    }

    private void setTrajectryFollower(Planet3D body, Color color) {
        Trajectory trajectory = new Trajectory();
        trajectory.setBody(body, color);
        trajectories.add(trajectory);
    }

    private void setCoordinateToObject(SphericStellarBody body, Planet3D planet) {
        DoubleVector3 vec = new DoubleVector3(body.getPosition().x / meterPerPixel/mpp2, body.getPosition().y / meterPerPixel/mpp2, body.getPosition().z / meterPerPixel/mpp2);

//        System.out.println(vec.y);
        planet.setPosition((float) vec.x, (float) vec.y, (float) vec.z);
    }

    private void pointCameraTo(SphericStellarBody body) {
        DoubleVector3 vec = new DoubleVector3(body.getPosition().x / meterPerPixel/mpp2, body.getPosition().y / meterPerPixel/mpp2, body.getPosition().z / meterPerPixel/mpp2);
        cam.position.set((float) vec.x, (float) vec.y+10, (float) vec.z+10);
        cam.lookAt((float) vec.x, (float) vec.y, (float) vec.z);
        cam.update();
    }

    public void draw() {
        modelBatch.begin(cam);

        for (String key : planets.keySet()) {
            Planet3D planet = planets.get(key);
            planet.draw(modelBatch, environment);
        }

        modelBatch.end();

        for(int i = 0; i < trajectories.size(); i++) {
            trajectories.get(i).draw(cam);
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

//       if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
        if(true) {
            try {
                for (int i = 0; i < 1200; i++) {
                    solarSystem.tick();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            uiStage.update();

            for(int i = 0; i < trajectories.size(); i++) {
                trajectories.get(i).update();
            }
        }

    }
}
