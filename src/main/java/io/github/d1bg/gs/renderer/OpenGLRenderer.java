package io.github.d1bg.gs.renderer;

import io.github.d1bg.gs.camera.Camera;
import io.github.d1bg.gs.light.AmbientLight;
import io.github.d1bg.gs.light.DirectionalLight;
import io.github.d1bg.gs.light.PointLight;
import io.github.d1bg.gs.light.SpotLight;
import io.github.d1bg.gs.scene.Scene;
import io.github.d1bg.gs.scene.SceneObject;
import io.github.d1bg.gs.scene.Transform;
import io.github.d1bg.gs.shaders.ShaderProgram;
import io.github.d1bg.gs.utils.ResourceLoader;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class OpenGLRenderer implements Renderer {
    private ShaderProgram shader;

    @Override
    public void init() {
        ResourceLoader resourceLoader = new ResourceLoader();

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        shader = new ShaderProgram(
                resourceLoader.loadResource("shaders/vertexShader.vert"),
                resourceLoader.loadResource("shaders/fragmentShader.frag")
        );
    }

    @Override
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void render(Scene scene, Camera camera) {
        shader.use();

        shader.setUniform("viewMatrix", camera.getViewMatrix());
        shader.setUniform("projectionMatrix", camera.getProjectionMatrix());

        setDirectionalLightUniforms(scene);

        for (SceneObject sceneObject : scene.getSceneObjects()) {
            Transform transform = sceneObject.getTransform();
            shader.setUniform("modelMatrix", transform.getModelMatrix());
            shader.setUniform("alpha", sceneObject.getAlpha());

            sceneObject.render(shader);
        }
    }

    private void setAmbientLightUniforms(Scene scene) {
        AmbientLight ambientLight = scene.getAmbientLight();
        shader.setUniform("ambientLight.ambient", ambientLight.getAmbient());
    }
    private void setDirectionalLightUniforms(Scene scene) {
        DirectionalLight directionalLight = scene.getDirectionalLight();
        shader.setUniform("directionalLight.direction", directionalLight.getDirection());
        shader.setUniform("directionalLight.ambient", directionalLight.getAmbient());
        shader.setUniform("directionalLight.diffuse", directionalLight.getDiffuse());
        shader.setUniform("directionalLight.specular", directionalLight.getSpecular());
    }
    private void setPointLightUniforms(Scene scene) {
        PointLight pointLight = scene.getPointLight();
        shader.setUniform("pointLight.position", pointLight.getPosition());
        shader.setUniform("pointLight.ambient", pointLight.getAmbient());
        shader.setUniform("pointLight.diffuse", pointLight.getDiffuse());
        shader.setUniform("pointLight.specular", pointLight.getSpecular());
        shader.setUniform("pointLight.constant", pointLight.getConstant());
        shader.setUniform("pointLight.linear", pointLight.getLinear());
        shader.setUniform("pointLight.quadratic", pointLight.getQuadratic());
    }
    private void setSpotLightUniforms(Scene scene) {
        SpotLight spotLight = scene.getSpotLight();
        shader.setUniform("spotLight.position", spotLight.getPosition());
        shader.setUniform("spotLight.direction", spotLight.getDirection());
        shader.setUniform("spotLight.ambient", spotLight.getAmbient());
        shader.setUniform("spotLight.diffuse", spotLight.getDiffuse());
        shader.setUniform("spotLight.specular", spotLight.getSpecular());
        shader.setUniform("spotLight.innerCutOff", spotLight.getInnerCutOff());
        shader.setUniform("spotLight.outerCutOff", spotLight.getOuterCutOff());
        shader.setUniform("spotLight.constant", spotLight.getConstant());
        shader.setUniform("spotLight.linear", spotLight.getLinear());
        shader.setUniform("spotLight.quadratic", spotLight.getQuadratic());
    }
    private void setShininessProperties(Camera camera) {
        shader.setUniform("viewPos", camera.getPosition());
        shader.setUniform("shininess", 16.0f);
        shader.setUniform("specularColor", new Vector3f(0.5f, 0.5f, 0.5f));
    }

    @Override
    public void cleanup() {
        shader.cleanup();
    }
}
