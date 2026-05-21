package io.github.d1bg.gs.demo.objects.cube;

import io.github.d1bg.gs.core.input.Action;
import io.github.d1bg.gs.core.input.InputHandler;
import io.github.d1bg.gs.demo.assets.block.CubeGeometry;
import io.github.d1bg.gs.material.Material;
import io.github.d1bg.gs.mesh.DrawMode;
import io.github.d1bg.gs.mesh.Mesh;
import io.github.d1bg.gs.model.MaterializedModelElement;
import io.github.d1bg.gs.model.ModelElement;
import io.github.d1bg.gs.scene.SceneObject;

public class Cube extends SceneObject {

    public Cube() {
        buildCube();
    }

    private void buildCube() {
        Mesh cube = new Mesh(DrawMode.TRIANGLES, new CubeGeometry());
        ModelElement rectangle = new MaterializedModelElement(cube, Material.getGoldMaterial());
        getModel().addElement(rectangle);
    }

    @Override
    public void update() {
        float rotSpeed = 0.5f;
        getTransform().rotateY(rotSpeed);

        float speed = 0.05f;
        float alpha = getAlpha();
        if (InputHandler.isActionPressed(Action.DEMO_INCREASE_ALPHA)) {
            setAlpha(clamp(alpha + speed));
        }
        if (InputHandler.isActionPressed(Action.DEMO_DECREASE_ALPHA)) {
            setAlpha(clamp(alpha - speed));
        }
    }

    private float clamp(float alpha) {
        return Math.clamp(alpha, 0.0f, 1.0f);
    }
}