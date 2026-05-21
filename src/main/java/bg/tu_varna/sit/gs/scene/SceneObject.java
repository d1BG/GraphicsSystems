package bg.tu_varna.sit.gs.scene;

import bg.tu_varna.sit.gs.model.Model;
import bg.tu_varna.sit.gs.shaders.ShaderProgram;

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
