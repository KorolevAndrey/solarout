package com.solarout.graphics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;

import javax.swing.text.View;

public class SoraoutGraphics extends ApplicationAdapter {

	private Viewer3D viewer;

	private UIStage uiStage;

	private InputMultiplexer multiplexer;

	@Override
	public void create () {
		uiStage = new UIStage();
		viewer = new Viewer3D(uiStage);

		multiplexer = new InputMultiplexer();

		multiplexer.addProcessor(uiStage);
		multiplexer.addProcessor(viewer.camController);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void render () {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		viewer.act(Gdx.graphics.getDeltaTime());
		viewer.draw();

		uiStage.act();
		uiStage.draw();
	}
}
