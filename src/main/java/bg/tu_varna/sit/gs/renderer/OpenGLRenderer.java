package bg.tu_varna.sit.gs.renderer;

import bg.tu_varna.sit.gs.camera.Camera;
import bg.tu_varna.sit.gs.scene.Scene;
import bg.tu_varna.sit.gs.scene.SceneObject;
import bg.tu_varna.sit.gs.scene.Transform;
import bg.tu_varna.sit.gs.shaders.ShaderProgram;
import bg.tu_varna.sit.gs.utils.ResourceLoader;

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

        for (SceneObject sceneObject : scene.getSceneObjects()) {
            Transform transform = sceneObject.getTransform();
            shader.setUniform("modelMatrix", transform.getModelMatrix());
            shader.setUniform("alpha", sceneObject.getAlpha());

            sceneObject.render(shader);
        }
    }

    @Override
    public void cleanup() {
        shader.cleanup();
    }
}
