package bg.tu_varna.sit.gs.light;

import org.joml.Vector3f;

public abstract class Light {
    private final Vector3f ambient = new Vector3f();
    private final Vector3f diffuse = new Vector3f();
    private final Vector3f specular = new Vector3f();

    public Vector3f getAmbient() {
        return ambient;
    }

    public Vector3f getDiffuse() {
        return diffuse;
    }

    public Vector3f getSpecular() {
        return specular;
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
}