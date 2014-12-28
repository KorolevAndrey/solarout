package com.solarout.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.solarout.engine.SphericStellarBody;
import com.solarout.engine.UniStarSystem;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by CyberJoe on 12/19/2014.
 */
public class UIStage extends Stage {

    private Label name;

    private Label xPos;
    private Label yPos;
    private Label zPos;

    private Label scalar;
    private Label scalarVec;

    private UniStarSystem solarSystem;

    private int currInfoIndex = 0;

    public UIStage() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        Label.LabelStyle textStyle = new Label.LabelStyle();
        textStyle.font = font;

        name = new Label("", textStyle);
        name.setWidth(100);
        name.setY(getHeight()-name.getHeight()-10f);
        addActor(name);

        xPos = new Label("", textStyle);
        xPos.setWidth(100);
        xPos.setY(name.getY()-xPos.getHeight()-10f);
        addActor(xPos);

        yPos = new Label("", textStyle);
        yPos.setWidth(100);
        yPos.setY(xPos.getY()-yPos.getHeight()-10f);
        addActor(yPos);

        zPos = new Label("", textStyle);
        zPos.setWidth(100);
        zPos.setY(yPos.getY()-zPos.getHeight()-10f);
        addActor(zPos);

        scalar = new Label("", textStyle);
        scalar.setWidth(100);
        scalar.setY(zPos.getY()-scalar.getHeight()-10f);
        addActor(scalar);

        scalarVec = new Label("", textStyle);
        scalarVec.setWidth(100);
        scalarVec.setY(scalar.getY()-scalarVec.getHeight()-10f);
        addActor(scalarVec);

        addListener(new InputListener() {
            public boolean keyUp (InputEvent event, int keycode) {
                if(keycode == 61) {
                    currInfoIndex++;
                    if(currInfoIndex>solarSystem.getStellarBodies().size()-1) {
                        currInfoIndex = 0;
                    }
                }
                return false;
            }
        });
    }

    public void setSystem(UniStarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    public void update() {
        if(solarSystem == null) return;

        Iterator it = solarSystem.getStellarBodies().entrySet().iterator();
        int iter = 0;
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            String bodyName = (String) pairs.getKey();
            SphericStellarBody solarBody = (SphericStellarBody) pairs.getValue();

            if(iter == currInfoIndex) {
                updateLbl(name, "Name: " + solarBody.getName());
                updateLbl(xPos, "X: " + solarBody.getPosition().x);
                updateLbl(yPos, "Y: " + solarBody.getPosition().y);
                updateLbl(zPos, "Z: " + solarBody.getPosition().z);
                updateLbl(scalar, "vel scalar: " + solarBody.getVelocity().getScalar());
                updateLbl(scalarVec, "vel vector: x:" + solarBody.getVelocity().getVector().x + ", y:" + solarBody.getVelocity().getVector().y + ", z:" + solarBody.getVelocity().getVector().z);
            }
            iter++;
        }
    }

    private void updateLbl(Label lbl, String value) {
        lbl.setText( value);
        lbl.setX(10f);
    }
}
