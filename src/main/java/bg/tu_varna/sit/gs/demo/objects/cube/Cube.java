package bg.tu_varna.sit.gs.demo.objects.cube;

import bg.tu_varna.sit.gs.core.input.Action;
import bg.tu_varna.sit.gs.core.input.InputHandler;
import bg.tu_varna.sit.gs.demo.assets.block.CubeGeometry;
import bg.tu_varna.sit.gs.demo.assets.demoHouse.RectangleGeometry;
import bg.tu_varna.sit.gs.demo.assets.demoHouse.TriangleGeometry;
import bg.tu_varna.sit.gs.material.Material;
import bg.tu_varna.sit.gs.mesh.DrawMode;
import bg.tu_varna.sit.gs.mesh.Mesh;
import bg.tu_varna.sit.gs.model.MaterializedModelElement;
import bg.tu_varna.sit.gs.model.ModelElement;
import bg.tu_varna.sit.gs.scene.SceneObject;
import org.joml.Vector3f;

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