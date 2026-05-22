package io.github.d1bg.gs.demo;

import io.github.d1bg.gs.demo.objects.cube.Block;
import io.github.d1bg.gs.demo.objects.cube.Cube;
import io.github.d1bg.gs.light.DirectionalLight;
import io.github.d1bg.gs.scene.Scene;
import org.joml.Vector3f;

import java.util.Random;

public class SceneBuilder {
    public static Scene build() {
        Scene scene = new Scene();

        DirectionalLight directionalLight = new DirectionalLight(
                new Vector3f(5f, -10f, 0f),
                new Vector3f(0.50f, 0.50f, 0.50f),
                new Vector3f(0.5f, 0.5f, 0.5f),
                new Vector3f(1.0f, 1.0f, 1.0f)
        );


        scene.setDirectionalLight(directionalLight);

        //chunk

        final int SIZE = 15;
        Vector3f position = new Vector3f(0f, 0f, 0f);
        Random random = new Random();
        for (int i = 0; i <= SIZE; i++) { // Y
            for  (int j = 0; j <= SIZE; j++) { // X
                for (int k = 0; k <= SIZE; k++) { // Z
                    if (random.nextBoolean()) {
                        if (i == SIZE) {
                            scene.addSceneObject(new Cube(Block.GRASS, new Vector3f(position.x + j, position.y + i, position.z + k)));
                        } else {
                            scene.addSceneObject(new Cube(Block.DIRT, new Vector3f(position.x + j, position.y + i, position.z + k)));
                        }

                    }
                }
            }
        }

        return scene;
    }
}
