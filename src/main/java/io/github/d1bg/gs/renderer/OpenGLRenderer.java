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

        float skyR = 0.43f;
        float skyG = 0.70f;
        float skyB = 0.80f;
        glClearColor(skyR, skyG, skyB, 1.0f);

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

    @Override
    public void cleanup() {
        shader.cleanup();
    }
}
