package bg.tu_varna.sit.gs.model;

import bg.tu_varna.sit.gs.material.Material;
import bg.tu_varna.sit.gs.mesh.Mesh;
import bg.tu_varna.sit.gs.shaders.ShaderProgram;

public class MaterializedModelElement extends ModelElement {
    private Material material;

    public MaterializedModelElement(Mesh mesh, Material material) {
        super(mesh);
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public void render(ShaderProgram shader) {
        applyMaterial(shader);
        super.render(shader);
    }

    public void applyMaterial(ShaderProgram shader) {
        shader.setUniform("material.ambient", material.getAmbient());
        shader.setUniform("material.diffuse", material.getDiffuse());
        shader.setUniform("material.specular", material.getSpecular());
        shader.setUniform("material.shininess", material.getShininess());
    }
}