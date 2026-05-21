package io.github.d1bg.gs.demo;

import io.github.d1bg.gs.demo.objects.cube.Cube;
import io.github.d1bg.gs.scene.Scene;
import io.github.d1bg.gs.scene.SceneObject;

public class SceneBuilder {
    public static Scene build() {
        Scene scene = new Scene();
        SceneObject cube = new Cube();
        scene.addSceneObject(cube);
        return scene;
    }
}
