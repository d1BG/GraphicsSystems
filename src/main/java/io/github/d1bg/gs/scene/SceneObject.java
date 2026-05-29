package io.github.d1bg.gs.scene;

import io.github.d1bg.gs.model.Model;
import io.github.d1bg.gs.shaders.ShaderProgram;

public abstract class SceneObject {
    private final Model model = new Model();
    private Transform transform = new Transform();
    private float alpha = 1.0f;

    public Model getModel() {
        return model;
    }

    public void render(ShaderProgram shader) {
        model.render(shader);
    }

    public void update(){}

    public void cleanup() {
        if (model != null) {
            model.cleanup();
        }
    }

    public Transform getTransform() {
        return transform;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
