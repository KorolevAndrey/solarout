package com.solarout.graphics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import javax.swing.text.View;

public class SoraoutGraphics extends ApplicationAdapter {

	private Viewer3D viewer;

	@Override
	public void create () {
		viewer = new Viewer3D();
	}

	@Override
	public void render () {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		viewer.act(Gdx.graphics.getDeltaTime());
		viewer.draw();
	}
}
