package io.github.d1bg.gs.light;

import org.joml.Vector3f;

public class SpotLight extends PointLight {
    private final Vector3f direction = new Vector3f();
    private float innerCutOff;
    private float outerCutOff;

    public SpotLight() {
        setCutOffDegrees(12.5f, 17.5f);
    }

    public SpotLight(Vector3f position, Vector3f direction) {
        super(position);
        setDirection(direction);
        setCutOffDegrees(12.5f, 17.5f);
    }

    public SpotLight(Vector3f position, Vector3f direction, Vector3f ambient, Vector3f diffuse, Vector3f specular, float innerCutOffDegrees, float outerCutOffDegrees) {
        super(position, ambient, diffuse, specular);
        setDirection(direction);
        setCutOffDegrees(innerCutOffDegrees, outerCutOffDegrees);
    }

    public SpotLight(Vector3f position, Vector3f direction, Vector3f ambient, Vector3f diffuse, Vector3f specular, float innerCutOffDegrees, float outerCutOffDegrees, float constant, float linear, float quadratic) {
        super(position, ambient, diffuse, specular, constant, linear, quadratic);
        setDirection(direction);
        setCutOffDegrees(innerCutOffDegrees, outerCutOffDegrees);
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f value) {
        direction.set(value).normalize();
    }

    public float getInnerCutOff() {
        return innerCutOff;
    }

    public float getOuterCutOff() {
        return outerCutOff;
    }

    public void setCutOffDegrees(float innerCutOffDegrees, float outerCutOffDegrees) {
        this.innerCutOff = (float) Math.cos(Math.toRadians(innerCutOffDegrees));
        this.outerCutOff = (float) Math.cos(Math.toRadians(outerCutOffDegrees));
    }
}
