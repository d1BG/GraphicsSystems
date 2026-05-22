package io.github.d1bg.gs.scene;

import io.github.d1bg.gs.light.AmbientLight;
import io.github.d1bg.gs.light.DirectionalLight;
import io.github.d1bg.gs.light.PointLight;
import io.github.d1bg.gs.light.SpotLight;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private AmbientLight ambientLight;
    private DirectionalLight directionalLight;
    private PointLight pointLight;
    private SpotLight spotLight;

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

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    public DirectionalLight getDirectionalLight() {
        return directionalLight;
    }

    public void setDirectionalLight(DirectionalLight directionalLight) {
        this.directionalLight = directionalLight;
    }

    public PointLight getPointLight() {
        return pointLight;
    }

    public void setPointLight(PointLight pointLight) {
        this.pointLight = pointLight;
    }

    public SpotLight getSpotLight() {
        return spotLight;
    }

    public void setSpotLight(SpotLight spotLight) {
        this.spotLight = spotLight;
    }
}
