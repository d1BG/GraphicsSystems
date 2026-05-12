package bg.tu_varna.sit.gs.mesh;


public abstract class Geometry {
    private float[] data;
    private VertexAttribute[] attributes;
    private int stride;

    public float[] getData() {
        return data;
    }

    public void setData(float[] data) {
        this.data = data;
    }

    public VertexAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(VertexAttribute[] attributes) {
        this.attributes = attributes;
    }

    public int getStride() {
        return stride;
    }

    public void setStride(int stride) {
        this.stride = stride;
    }

    public int getVertexCount() {
        return data.length / (stride / Float.BYTES);
    }
}
