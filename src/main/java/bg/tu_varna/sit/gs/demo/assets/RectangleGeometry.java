package bg.tu_varna.sit.gs.demo.assets;

import bg.tu_varna.sit.gs.mesh.Geometry;
import bg.tu_varna.sit.gs.mesh.VertexAttribute;

public class RectangleGeometry extends Geometry {
    public RectangleGeometry() {
        setData(createVertices());
        setAttributes(new VertexAttribute[]{
                new VertexAttribute(0, 3),
                new VertexAttribute(1, 3)
        });
        setStride(6 * Float.BYTES); // 3 for position + 3 for color
    }

    private float[] createVertices() {
        return new float[]{
                // x y z
                -0.5f, -0.7f, 0f, 0.5f, 0.5f,0.5f,
                0.5f, -0.7f, 0f, 0.5f, 0.5f,0.5f,
                0.5f, 0.3f, 0f, 0.5f, 0.5f,0.5f,

                -0.5f, -0.7f, 0f, 0.5f, 0.5f,0.5f,
                0.5f, 0.3f, 0f, 0.5f, 0.5f,0.5f,
                -0.5f, 0.3f, 0f, 0.5f, 0.5f,0.5f
        };
    }
}
