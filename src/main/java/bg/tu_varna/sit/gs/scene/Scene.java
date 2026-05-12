package bg.tu_varna.sit.gs.scene;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final List<SceneObject> sceneObjects = new ArrayList<>();

    public void addSceneObject(SceneObject sceneObject) {
        sceneObjects.add(sceneObject);
    }

    public List<SceneObject> getSceneObjects() {
        return sceneObjects;
    }

    public void update() {
        for (SceneObject object : sceneObjects) {
            object.update();
        }
    }
}
