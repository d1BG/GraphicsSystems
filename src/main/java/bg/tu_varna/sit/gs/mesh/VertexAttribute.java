package bg.tu_varna.sit.gs.mesh;

public class VertexAttribute {

    private final int location;
    private final int componentSize;

    public VertexAttribute(int location, int componentSize) {
        this.location = location;
        this.componentSize = componentSize;
    }

    public int getLocation() {
        return location;
    }

    public int getComponentSize() {
        return componentSize;
    }
}