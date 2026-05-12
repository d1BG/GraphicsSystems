package bg.tu_varna.sit.gs.demo;

import bg.tu_varna.sit.gs.demo.objects.House;
import bg.tu_varna.sit.gs.scene.Scene;
import bg.tu_varna.sit.gs.scene.SceneObject;
import org.joml.Vector3f;

public class SceneBuilder {

    public static Scene build() {

        Scene scene = new Scene();
        SceneObject house = new House();
        scene.addSceneObject(house);

        return scene;
    }
}
