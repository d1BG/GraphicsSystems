package io.github.d1bg.gs.demo.assets.fullBlock;

import io.github.d1bg.gs.mesh.Geometry;
import io.github.d1bg.gs.mesh.VertexAttribute;

public class RightSurface extends Geometry {
    public RightSurface() {
        setData(createVertices());
        setAttributes(new VertexAttribute[]{
                new VertexAttribute(0, 3),
                new VertexAttribute(1, 2)
        });
        setStride(5 * Float.BYTES); // 3 for position + 2 u v
    }

    private float[] createVertices() {
        return new float[] {
                1, 1, 0, 1, 1, //D
                1, 0, 0, 1, 0, //B
                1, 0, 1, 0, 0, //G

                1, 1, 0, 1, 1, //D
                1, 0, 1, 0, 0, //G
                1, 1, 1, 0, 1  //H
        };
    }
}
