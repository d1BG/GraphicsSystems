package bg.tu_varna.sit.gs.camera;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public abstract class Camera {
    private Vector3f position = new Vector3f(0, 0, 3);
    private Vector3f target = new Vector3f(0, 0, 0);
    private Vector3f up = new Vector3f(0, 1, 0);

    private final Matrix4f viewMatrix = new Matrix4f();
    private final Matrix4f projectionMatrix = new Matrix4f();

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setTarget(Vector3f target) {
        this.target =  target;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }


    public Matrix4f getViewMatrix() {
        viewMatrix.identity().lookAt(position, target, up);
        return viewMatrix;
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public abstract void updateProjection();
}
