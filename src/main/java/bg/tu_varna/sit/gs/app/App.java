package bg.tu_varna.sit.gs.app;

import bg.tu_varna.sit.gs.camera.Camera;
import bg.tu_varna.sit.gs.camera.PerspectiveCamera;
import bg.tu_varna.sit.gs.core.Engine;
import bg.tu_varna.sit.gs.core.Window;
import bg.tu_varna.sit.gs.demo.SceneBuilder;
import bg.tu_varna.sit.gs.renderer.OpenGLRenderer;
import bg.tu_varna.sit.gs.renderer.Renderer;
import bg.tu_varna.sit.gs.scene.Scene;
import org.joml.Vector3f;

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

        camera.setPosition(new Vector3f(0.0f, 0.0f, 2.0f));
    }

    @Override
    protected void update() {
        scene.update();
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
