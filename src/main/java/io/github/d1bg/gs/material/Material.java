package io.github.d1bg.gs.material;

import org.joml.Vector3f;

public class Material {
    private final Vector3f ambient = new Vector3f();
    private final Vector3f diffuse = new Vector3f();
    private final Vector3f specular = new Vector3f();
    private float shininess;

    public Material(Vector3f ambient, Vector3f diffuse, Vector3f specular, float shininess) {
        setAmbient(ambient);
        setDiffuse(diffuse);
        setSpecular(specular);
        setShininess(shininess);
    }

    public static Material getRedPlasticMaterial() {
        return new Material(new Vector3f(0.2f, 0.03f, 0.03f), new Vector3f(0.8f, 0.1f, 0.1f), new Vector3f(0.4f, 0.4f, 0.4f), 24.0f);
    }

    public static Material getMatteRedMaterial() {
        return new Material(new Vector3f(0.18f, 0.03f, 0.03f), new Vector3f(0.75f, 0.12f, 0.12f), new Vector3f(0.08f, 0.08f, 0.08f), 8.0f);
    }

    public static Material getWhiteCeramicMaterial() {
        return new Material(new Vector3f(0.25f, 0.25f, 0.25f), new Vector3f(0.85f, 0.85f, 0.85f), new Vector3f(0.45f, 0.45f, 0.45f), 32.0f);
    }

    public static Material getGoldMaterial() {
        return new Material(new Vector3f(0.24725f, 0.1995f, 0.0745f), new Vector3f(0.75164f, 0.60648f, 0.22648f), new Vector3f(0.628281f, 0.555802f, 0.366065f), 51.2f);
    }

    public Vector3f getAmbient() {
        return ambient;
    }

    public Vector3f getDiffuse() {
        return diffuse;
    }

    public Vector3f getSpecular() {
        return specular;
    }

    public float getShininess() {
        return shininess;
    }

    public void setAmbient(Vector3f value) {
        ambient.set(value);
    }

    public void setDiffuse(Vector3f value) {
        diffuse.set(value);
    }

    public void setSpecular(Vector3f value) {
        specular.set(value);
    }

    public void setShininess(float shininess) {
        this.shininess = shininess;
    }
}