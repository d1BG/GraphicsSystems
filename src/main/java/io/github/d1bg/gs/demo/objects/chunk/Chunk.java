package io.github.d1bg.gs.demo.objects.chunk;

import io.github.d1bg.gs.core.input.Action;
import io.github.d1bg.gs.core.input.InputHandler;
import io.github.d1bg.gs.demo.assets.fullBlock.*;
import io.github.d1bg.gs.demo.objects.cube.Block;
import io.github.d1bg.gs.demo.objects.cube.Cube;
import io.github.d1bg.gs.material.Texture;
import io.github.d1bg.gs.mesh.DrawMode;
import io.github.d1bg.gs.mesh.Mesh;
import io.github.d1bg.gs.model.ModelElement;
import io.github.d1bg.gs.model.TexturedModelElement;
import io.github.d1bg.gs.scene.SceneObject;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Chunk extends SceneObject {
    private Vector3f position;
    private final int SIZE = 15;

    public Chunk(Vector3f position) {
        this.position = position;
        buildChunk();
    }

    private void buildChunk() {
        List<SceneObject> blocks = new ArrayList<>();
    }

    @Override
    public void update() {}
}