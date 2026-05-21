package io.github.d1bg.gs.demo.objects.cube;

import io.github.d1bg.gs.core.input.Action;
import io.github.d1bg.gs.core.input.InputHandler;
import io.github.d1bg.gs.demo.assets.fullBlock.*;
import io.github.d1bg.gs.material.Texture;
import io.github.d1bg.gs.mesh.DrawMode;
import io.github.d1bg.gs.mesh.Mesh;
import io.github.d1bg.gs.model.ModelElement;
import io.github.d1bg.gs.model.TexturedModelElement;
import io.github.d1bg.gs.scene.SceneObject;
import org.joml.Vector3f;

public class Cube extends SceneObject {

    public Cube() {
        buildCube();
    }

    Mesh front = new Mesh(DrawMode.TRIANGLES, new FrontSurface());
    Mesh back = new Mesh(DrawMode.TRIANGLES, new BackSurface());
    Mesh left = new Mesh(DrawMode.TRIANGLES, new LeftSurface());
    Mesh right = new Mesh(DrawMode.TRIANGLES, new RightSurface());
    Mesh top = new Mesh(DrawMode.TRIANGLES, new TopSurface());
    Mesh bottom = new Mesh(DrawMode.TRIANGLES, new BottomSurface());

    private void buildCube() {
        Texture sideTexture = new Texture("textures/grass_block_side.png");
        Texture topTexture = new Texture("textures/grass_block_top.png");
        Texture bottomTexture = new Texture("textures/dirt.png");

        ModelElement frontMesh = new TexturedModelElement(front, sideTexture);
        ModelElement backMesh = new TexturedModelElement(back, sideTexture);
        ModelElement leftMesh = new TexturedModelElement(left, sideTexture);
        ModelElement rightMesh = new TexturedModelElement(right, sideTexture);
        ModelElement topMesh = new TexturedModelElement(top, topTexture);
        ModelElement bottomMesh = new TexturedModelElement(bottom, bottomTexture);

        getModel().addElement(frontMesh);
        getModel().addElement(backMesh);
        getModel().addElement(leftMesh);
        getModel().addElement(rightMesh);
        getModel().addElement(topMesh);
        getModel().addElement(bottomMesh);

        getTransform().setPivot(new Vector3f(0.5f));
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