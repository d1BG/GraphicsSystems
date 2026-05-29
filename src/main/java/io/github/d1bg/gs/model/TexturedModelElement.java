package io.github.d1bg.gs.model;

import io.github.d1bg.gs.material.TextureArray;
import io.github.d1bg.gs.mesh.Mesh;
import io.github.d1bg.gs.shaders.ShaderProgram;

public class TexturedModelElement extends ModelElement {
    private TextureArray texture;
    public TexturedModelElement(Mesh mesh, TextureArray texture) {
        super(mesh);
        this.texture = texture;
    }

    public void render(ShaderProgram shader) {
        texture.bind(0);
        shader.setUniform("ourTextureArr", 0);
        super.render(shader);
    }
}