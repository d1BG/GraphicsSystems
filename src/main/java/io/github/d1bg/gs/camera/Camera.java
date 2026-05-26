package io.github.d1bg.gs.camera;

import io.github.d1bg.gs.core.LwjglImGuiLayer;
import io.github.d1bg.gs.core.input.Action;
import io.github.d1bg.gs.core.input.InputHandler;
import io.github.d1bg.gs.demo.assets.biome.BiomeGeneratorFactory;
import io.github.d1bg.gs.demo.assets.biome.Biomes;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static io.github.d1bg.gs.core.LwjglImGuiLayer.renderDistance;
import static io.github.d1bg.gs.demo.SceneBuilder.*;

public abstract class Camera {
    private Vector3f position = new Vector3f(0, 90, 0);
    private Vector3f target = new Vector3f(0.5f, 0.5f, 0.5f);
    private Vector3f up = new Vector3f(0, 1, 0);
    private Vector3f forward = new Vector3f();
    private Vector3f right = new Vector3f();

    private float yaw = -90.0f;
    private float pitch = 0.0f;

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

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getTarget() {
        return target;
    }

    public Vector3f getUp() {
        return up;
    }

    public Matrix4f getViewMatrix() {
        viewMatrix.identity().lookAt(position, target, up);
        return viewMatrix;
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public abstract void updateProjection();

    public void update() {
        if (InputHandler.isActionPressed(Action.CAMERA_MOVE_LEFT)) {
            move(MoveDirection.LEFT);
        }

        if (InputHandler.isActionPressed(Action.CAMERA_MOVE_RIGHT)) {
            move(MoveDirection.RIGHT);
        }

        if (InputHandler.isActionPressed(Action.CAMERA_MOVE_FORWARD)) {
            move(MoveDirection.BACKWARD);
        }

        if (InputHandler.isActionPressed(Action.CAMERA_MOVE_BACKWARD)) {
            move(MoveDirection.FORWARD);
        }

        if (InputHandler.isActionHeld(Action.LOCK_CURSOR)) {
            InputHandler.lockedMouseToggle();
        }

        float sensitivity = 1.0f;
        float dx = InputHandler.getMouseDX() * sensitivity;
        float dy = InputHandler.getMouseDY() * sensitivity;

        if (dx != 0.0f || dy != 0.0f) {
            rotate(dx, dy);
        }

        float rotSpeed = 1f;
        if (InputHandler.isActionPressed(Action.CAMERA_ROTATE_UP)) rotate(0, rotSpeed);
        if (InputHandler.isActionPressed(Action.CAMERA_ROTATE_DOWN)) rotate(0, -rotSpeed);
        if (InputHandler.isActionPressed(Action.CAMERA_ROTATE_LEFT)) rotate(-rotSpeed, 0);
        if (InputHandler.isActionPressed(Action.CAMERA_ROTATE_RIGHT)) rotate(rotSpeed, 0);

        for (int i = -renderDistance.get();i <= renderDistance.get(); i++) {
            for (int j = -renderDistance.get(); j <= renderDistance.get(); j++) {
                buildChunk((int) Math.ceil(getPosition().x) + (i * 16), (int) Math.ceil(getPosition().z) + (j * 16));
            }
        }

        LwjglImGuiLayer.position = "X: " + String.format("%01f", position.x) + "; Y: " + String.format("%01f", position.y) + "; Z: " + String.format("%01f", position.z);
        Biomes b = BiomeGeneratorFactory.getBiome((int) position.x, (int) position.z);
        LwjglImGuiLayer.biome = "Biome: " + b + " (Influence: " + String.format("%01f", b.getInfluence()) + ")";
    }

    public void move(MoveDirection moveDirection) {
        float MOVE_SPEED = 0.1f;

        if (InputHandler.isActionPressed(Action.CAMERA_MOVE_SPRINT)) {
            MOVE_SPEED = 0.5f;
        }

        target.sub(position, forward).normalize();

        forward.cross(up, right).normalize();

        Vector3f movement = new Vector3f();

        switch (moveDirection) {
            case FORWARD:
                forward.mul(-MOVE_SPEED, movement);
                break;
            case BACKWARD:
                forward.mul(MOVE_SPEED, movement);
                break;
            case RIGHT:
                right.mul(MOVE_SPEED, movement);
                break;
            case LEFT:
                right.mul(-MOVE_SPEED, movement);
                break;
        }

        // Apply movement to both position and target to maintain the view direction
        position.add(movement);
        target.add(movement);
    }

    public void rotate(float xOffset, float yOffset) {
        yaw += xOffset;
        pitch += yOffset;

        // Constrain the pitch to prevent gimbal lock (flipping upside down)
        if (pitch > 89.0f) {
            pitch = 89.0f;
        }
        if (pitch < -89.0f) {
            pitch = -89.0f;
        }

        updateCameraVectors();
    }

    private void updateCameraVectors() {
        forward.x = (float) (Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        forward.y = (float) Math.sin(Math.toRadians(pitch));
        forward.z = (float) (Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        forward.normalize();

        position.add(forward, target);
    }

    public Vector3f getForward() {
        return forward;
    }

    public Vector3f getRight() {
        return right;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
}
