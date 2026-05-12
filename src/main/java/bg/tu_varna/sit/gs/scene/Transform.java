package bg.tu_varna.sit.gs.scene;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.joml.Math.toRadians;

public class Transform {
    private Vector3f translation = new Vector3f();
    private Vector3f rotation = new Vector3f();
    private Vector3f scale = new Vector3f(1, 1, 1);
    private Vector3f pivot = new Vector3f();
    private final Matrix4f modelMatrix = new Matrix4f();
    private boolean transformChanged = true;

    public void markTransformChanged() {
        transformChanged = true;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
        markTransformChanged();
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
        markTransformChanged();
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
        markTransformChanged();
    }

    public void setPivot(Vector3f pivot) {
        this.pivot = pivot;
        markTransformChanged();
    }

    public void rotateY(float angle) {
        rotation.y += toRadians(angle);
        markTransformChanged();
    }

    public void rotateX(float angle) {
        rotation.x += toRadians(angle);
        markTransformChanged();
    }

    public void rotateZ(float angle) {
        rotation.z += toRadians(angle);
        markTransformChanged();
    }

    public Matrix4f getModelMatrix() {
        if (transformChanged) {
            modelMatrix.identity()
                    .translate(translation)
                    .translate(pivot)
                    .rotateZ(rotation.z)
                    .rotateY(rotation.y)
                    .rotateX(rotation.x)
                    .translate(-pivot.x, -pivot.y, -pivot.z)
                    .scale(scale);
            transformChanged = false;
        }
        return modelMatrix;
    }
}
