package com.solarout.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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

    private Label xPos;
    private Label yPos;
    private Label zPos;

    private Label scalar;

    public UIStage() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        Label.LabelStyle textStyle = new Label.LabelStyle();
        textStyle.font = font;

        xPos = new Label("", textStyle);
        xPos.setWidth(100);
        xPos.setY(getHeight()-xPos.getHeight()-10f);
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
    }

    public void update(UniStarSystem solarSystem) {
//        Iterator it = solarSystem.getStellarBodies().entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pairs = (Map.Entry) it.next();
//            String bodyName = (String) pairs.getKey();
//            SphericStellarBody solarBody = (SphericStellarBody) pairs.getValue();

            SphericStellarBody earth = solarSystem.getStellarBodies().get("earth");
            SphericStellarBody moon = solarSystem.getStellarBodies().get("moon");

//            updateLbl(xPos, "X: " + solarBody.getPosition().x);
//            updateLbl(yPos, "Y: " + solarBody.getPosition().y);
//            updateLbl(zPos, "Z: " + solarBody.getPosition().z);
//            updateLbl(scalar, "SCALAR: " + solarBody.getVelocity().getScalar());
                updateLbl(scalar, "SCALAR: " + (earth.getPosition().len() - moon.getPosition().len()));

//        }
    }

    private void updateLbl(Label lbl, String value) {
        lbl.setText( value);
        lbl.setX(getWidth()-lbl.getWidth()-5f);
    }
}
