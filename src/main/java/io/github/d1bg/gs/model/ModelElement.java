package io.github.d1bg.gs.model;

import io.github.d1bg.gs.mesh.Mesh;
import io.github.d1bg.gs.shaders.ShaderProgram;


public class ModelElement {
    private final Mesh mesh;

    public ModelElement(Mesh mesh) {
        this.mesh = mesh;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void render(ShaderProgram shader) {
        mesh.render();
    }

    public void cleanup() {
        if (mesh != null) {
            mesh.cleanup();
        }
    }
}
