package com.solarout.graphics.actors;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.solarout.engine.MyVector3;

/**
 * Created by azakhary on 12/22/2014.
 */
public class Trajectory {

    private MyVector3[] trajectoryPoints;
    private int tpIndex = 0;
    private Planet3D followingBody;


    private ShapeRenderer shapeRenderer;

    private Color color;

    public Trajectory() {
        shapeRenderer = new ShapeRenderer();
    }

    public void setBody(Planet3D body, Color color) {
        trajectoryPoints = new MyVector3 [200];
        followingBody = body;
        tpIndex = 0;
        this.color = color;
        //CatmullRomSpline<MyVector3 > myCatmull = new CatmullRomSpline<MyVector3 >(trajectoryPoints, true);
    }

    public void update() {
        if(followingBody != null) {
            if(tpIndex >= trajectoryPoints.length) {
                tpIndex = trajectoryPoints.length-1;
                for(int i = 0; i < trajectoryPoints.length-1; i++) {
                    trajectoryPoints[i] = trajectoryPoints[i+1];
                }
                trajectoryPoints[tpIndex] = new MyVector3(followingBody.getPosition());
            } else {
                trajectoryPoints[tpIndex++] = new MyVector3(followingBody.getPosition());
            }

        }
    }

    public void draw(Camera cam) {
        if(followingBody != null) {
            for(int i = 0; i < trajectoryPoints.length-1; i++) {
                if(trajectoryPoints[i+1] == null) break;

                shapeRenderer.setProjectionMatrix(cam.combined);

                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(color);
                shapeRenderer.line(trajectoryPoints[i].toVector3(), trajectoryPoints[i+1].toVector3());
                shapeRenderer.end();
            }
        }
    }
}
