package com.solarout.engine;

import com.badlogic.gdx.math.Vector3 ;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by aram on 12/15/2014.
 */

abstract public class MaterialPoint {

    protected MyVector3  position;

    protected String name;

    /**
     * @param position position of the material point
     */

    public MaterialPoint(MyVector3  position) {
        this.position = position;
        this.assignRandomName();
    }

    public void assignRandomName() {
        SecureRandom random = new SecureRandom();
        this.name = "Object-" + new BigInteger(30, random).toString(32);
    }

    public MaterialPoint(MyVector3  position, String name) {
        this.position = position;
        this.name = name;
    }

    public MyVector3  getPosition() {
        return position;
    }

    public void setPosition(MyVector3  position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
