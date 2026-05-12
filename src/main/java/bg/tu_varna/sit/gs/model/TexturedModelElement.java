package bg.tu_varna.sit.gs.model;

import bg.tu_varna.sit.gs.mesh.Mesh;
import bg.tu_varna.sit.gs.shaders.ShaderProgram;
import bg.tu_varna.sit.gs.material.Texture;
public class TexturedModelElement extends ModelElement {
    private Texture texture;
    public TexturedModelElement(Mesh mesh, Texture texture) {
        super(mesh);
        this.texture = texture;
    }
    public void render(ShaderProgram shader) {
        texture.bind(0);
        shader.setUniform("ourTexture", 0);
        super.render(shader);
    }
}