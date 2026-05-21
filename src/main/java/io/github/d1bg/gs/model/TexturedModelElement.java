package io.github.d1bg.gs.model;

import io.github.d1bg.gs.mesh.Mesh;
import io.github.d1bg.gs.shaders.ShaderProgram;
import io.github.d1bg.gs.material.Texture;
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