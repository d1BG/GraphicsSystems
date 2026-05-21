package io.github.d1bg.gs.demo.objects.cube;

import io.github.d1bg.gs.core.input.Action;
import io.github.d1bg.gs.core.input.InputHandler;
import io.github.d1bg.gs.demo.assets.block.*;
import io.github.d1bg.gs.material.Texture;
import io.github.d1bg.gs.mesh.DrawMode;
import io.github.d1bg.gs.mesh.Mesh;
import io.github.d1bg.gs.model.ModelElement;
import io.github.d1bg.gs.model.TexturedModelElement;
import io.github.d1bg.gs.scene.SceneObject;

public class Cube extends SceneObject {

    public Cube() {
        buildCube();
    }

    private void buildCube() {
        Texture brick = new Texture("textures/roof-texture.jpg");

        Mesh front = new Mesh(DrawMode.TRIANGLES, new FrontSurface());
        Mesh back = new Mesh(DrawMode.TRIANGLES, new BackSurface());
        Mesh left = new Mesh(DrawMode.TRIANGLES, new LeftSurface());
        Mesh right = new Mesh(DrawMode.TRIANGLES, new RightSurface());
        Mesh top = new Mesh(DrawMode.TRIANGLES, new TopSurface());
        Mesh bottom = new Mesh(DrawMode.TRIANGLES, new BottomSurface());

        ModelElement frontMesh = new TexturedModelElement(front, brick);
        ModelElement backMesh = new TexturedModelElement(back, brick);
        ModelElement leftMesh = new TexturedModelElement(left, brick);
        ModelElement rightMesh = new TexturedModelElement(right, brick);
        ModelElement topMesh = new TexturedModelElement(top, brick);
        ModelElement bottomMesh = new TexturedModelElement(bottom, brick);

        getModel().addElement(frontMesh);
        getModel().addElement(backMesh);
        getModel().addElement(leftMesh);
        getModel().addElement(rightMesh);
        getModel().addElement(topMesh);
        getModel().addElement(bottomMesh);
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