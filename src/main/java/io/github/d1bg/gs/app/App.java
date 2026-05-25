package io.github.d1bg.gs.app;

import io.github.d1bg.gs.camera.Camera;
import io.github.d1bg.gs.camera.PerspectiveCamera;
import io.github.d1bg.gs.core.Engine;
import io.github.d1bg.gs.core.LwjglImGuiLayer;
import io.github.d1bg.gs.core.Window;
import io.github.d1bg.gs.demo.SceneBuilder;
import io.github.d1bg.gs.renderer.OpenGLRenderer;
import io.github.d1bg.gs.renderer.Renderer;
import io.github.d1bg.gs.scene.Scene;

public class App extends Engine {
    private final int width, height;
    private final String title;

    private Renderer renderer;
    private Scene scene;
    private Camera camera;

    private LwjglImGuiLayer imGuiLayer;

    public App(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    @Override
    protected void init() {
        Window window = new Window(width, height, title);

        setWindow(window);

        imGuiLayer = new LwjglImGuiLayer();
        imGuiLayer.initImGui(window.getHandle());

        scene = SceneBuilder.build();

        renderer = new OpenGLRenderer();
        renderer.init();

        camera = new PerspectiveCamera(
                (float) Math.toRadians(110.0),
                0.05f, 500f,
                getWindow().getWidth(), getWindow().getHeight()
        );
        scene.setCamera(camera);
    }

    @Override
    protected void update() {
        scene.update();
        camera.update();
    }

    @Override
    protected void render() {
        renderer.clear();
        renderer.render(scene, camera);

        imGuiLayer.renderImGui();
    }

    @Override
    protected void cleanup() {
        imGuiLayer.destroyImGui();
        renderer.cleanup();
        getWindow().destroy();
    }
}
