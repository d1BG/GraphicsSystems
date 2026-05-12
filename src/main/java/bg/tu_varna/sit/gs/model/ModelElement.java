package bg.tu_varna.sit.gs.model;

import bg.tu_varna.sit.gs.mesh.Mesh;
import bg.tu_varna.sit.gs.shaders.ShaderProgram;


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
}
