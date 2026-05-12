package bg.tu_varna.sit.gs.light;

import org.joml.Vector3f;

public class AmbientLight extends Light {
    public AmbientLight() {
    }

    public AmbientLight(Vector3f ambient) {
        setAmbient(ambient);
    }
}