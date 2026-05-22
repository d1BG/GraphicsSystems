package io.github.d1bg.gs.demo;

import io.github.d1bg.gs.demo.objects.cube.Cube;
import io.github.d1bg.gs.light.DirectionalLight;
import io.github.d1bg.gs.scene.Scene;
import io.github.d1bg.gs.scene.SceneObject;
import org.joml.Vector3f;

public class SceneBuilder {
    public static Scene build() {
        Scene scene = new Scene();
        SceneObject cube = new Cube();
        scene.addSceneObject(cube);

        DirectionalLight directionalLight = new DirectionalLight(
                new Vector3f(5f, -10f, 0f),
                new Vector3f(0.50f, 0.50f, 0.50f),
                new Vector3f(0.5f, 0.5f, 0.5f),
                new Vector3f(1.0f, 1.0f, 1.0f)
        );


        scene.setDirectionalLight(directionalLight);
        return scene;
    }
}
