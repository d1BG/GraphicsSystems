package io.github.d1bg.gs.app;

import io.github.d1bg.gs.camera.Camera;
import io.github.d1bg.gs.camera.PerspectiveCamera;
import io.github.d1bg.gs.core.Engine;
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

    public App(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    @Override
    protected void init() {
        setWindow(new Window(width, height, title));

        scene = SceneBuilder.build();

        renderer = new OpenGLRenderer();
        renderer.init();

        camera = new PerspectiveCamera(
                (float) Math.toRadians(60.0),
                0.1f, 100f,
                getWindow().getWidth(), getWindow().getHeight()
        );
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
    }

    @Override
    protected void cleanup() {
        renderer.cleanup();
        getWindow().destroy();
    }
}
