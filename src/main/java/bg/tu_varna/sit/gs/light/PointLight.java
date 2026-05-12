package bg.tu_varna.sit.gs.light;

import org.joml.Vector3f;

public class PointLight extends Light {
    private final Vector3f position = new Vector3f();
    private float constant = 1.0f;
    private float linear = 0.09f;
    private float quadratic = 0.032f;

    public PointLight() {
    }

    public PointLight(Vector3f position) {
        setPosition(position);
    }

    public PointLight(Vector3f position, Vector3f ambient, Vector3f diffuse, Vector3f specular) {
        setPosition(position);
        setAmbient(ambient);
        setDiffuse(diffuse);
        setSpecular(specular);
    }

    public PointLight(Vector3f position, Vector3f ambient, Vector3f diffuse, Vector3f specular, float constant, float linear, float quadratic) {
        setPosition(position);
        setAmbient(ambient);
        setDiffuse(diffuse);
        setSpecular(specular);
        setAttenuation(constant, linear, quadratic);
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f value) {
        position.set(value);
    }

    public float getConstant() {
        return constant;
    }

    public void setConstant(float constant) {
        this.constant = constant;
    }

    public float getLinear() {
        return linear;
    }

    public void setLinear(float linear) {
        this.linear = linear;
    }

    public float getQuadratic() {
        return quadratic;
    }

    public void setQuadratic(float quadratic) {
        this.quadratic = quadratic;
    }

    public void setAttenuation(float constant, float linear, float quadratic) {
        this.constant = constant;
        this.linear = linear;
        this.quadratic = quadratic;
    }
}