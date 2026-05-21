package io.github.d1bg.gs.light;

import org.joml.Vector3f;

public class DirectionalLight extends Light {
    private final Vector3f direction = new Vector3f();

    public DirectionalLight() {
    }

    public DirectionalLight(Vector3f direction) {
        setDirection(direction);
    }

    public DirectionalLight(Vector3f direction, Vector3f ambient, Vector3f diffuse, Vector3f specular) {
        setDirection(direction);
        setAmbient(ambient);
        setDiffuse(diffuse);
        setSpecular(specular);
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f value) {
        direction.set(value).normalize();
    }
}