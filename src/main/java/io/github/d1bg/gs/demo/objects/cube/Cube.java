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

import java.util.ArrayList;

public class Cube extends SceneObject {
    private Vector3f position;
    private Mesh front;
    private Mesh back;
    private Mesh left;
    private Mesh right;
    private Mesh top;
    private Mesh bottom;

    public Cube(Block block, Vector3f position) {
        this.position = position;
        this.front = new Mesh(DrawMode.TRIANGLES, new FrontSurface(position));
        this.back = new Mesh(DrawMode.TRIANGLES, new BackSurface(position));
        this.left = new Mesh(DrawMode.TRIANGLES, new LeftSurface(position));
        this.right = new Mesh(DrawMode.TRIANGLES, new RightSurface(position));
        this.top = new Mesh(DrawMode.TRIANGLES, new TopSurface(position));
        this.bottom = new Mesh(DrawMode.TRIANGLES, new BottomSurface(position));

        switch (block) {
            case GRASS:
                buildCube(
                        "textures/grass_block_side.png",
                        "textures/grass_block_top.png",
                        "textures/dirt.png"
                );
                break;
            case DIRT:
                buildCube("textures/dirt.png");
                break;
            case STONE:
                buildCube("textures/stone.png");
                break;
        }
    }



    private void buildCube(String texture) {
        Texture singleTexture = new Texture(texture);

        ArrayList<ModelElement> walls = new ArrayList<>();
        walls.add(new TexturedModelElement(front, singleTexture));
        walls.add(new TexturedModelElement(back, singleTexture));
        walls.add(new TexturedModelElement(left, singleTexture));
        walls.add(new TexturedModelElement(right, singleTexture));
        walls.add(new TexturedModelElement(top, singleTexture));
        walls.add(new TexturedModelElement(bottom, singleTexture));

        for (ModelElement wall : walls) {
            getModel().addElement(wall);
        }

        getTransform().setPivot(position.add(new Vector3f(0.5f)));
    }

    private void buildCube(String sideTexture, String topTexture,  String bottomTexture) {
        Texture sTexture = new Texture(sideTexture);
        Texture tTexture = new Texture(topTexture);
        Texture bTexture = new Texture(bottomTexture);

        ArrayList<ModelElement> walls = new ArrayList<>();
        walls.add(new TexturedModelElement(front, sTexture));
        walls.add(new TexturedModelElement(back, sTexture));
        walls.add(new TexturedModelElement(left, sTexture));
        walls.add(new TexturedModelElement(right, sTexture));
        walls.add(new TexturedModelElement(top, tTexture));
        walls.add(new TexturedModelElement(bottom, bTexture));

        for (ModelElement wall : walls) {
            getModel().addElement(wall);
        }

        getTransform().setPivot(position.add(new Vector3f(0.5f)));
    }

    @Override
    public void update() {}
}