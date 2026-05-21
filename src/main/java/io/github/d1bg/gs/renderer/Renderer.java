package io.github.d1bg.gs.renderer;

import io.github.d1bg.gs.camera.Camera;
import io.github.d1bg.gs.scene.Scene;

public interface Renderer {
    void init();

    void clear();

    void render(Scene scene, Camera camera);

    void cleanup();
}
