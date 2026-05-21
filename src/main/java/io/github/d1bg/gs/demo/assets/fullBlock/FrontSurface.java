package io.github.d1bg.gs.demo.assets.fullBlock;

import io.github.d1bg.gs.mesh.Geometry;
import io.github.d1bg.gs.mesh.VertexAttribute;

public class FrontSurface extends Geometry {
    public FrontSurface() {
        setData(createVertices());
        setAttributes(new VertexAttribute[]{
                new VertexAttribute(0, 3),
                new VertexAttribute(1, 2)
        });
        setStride(5 * Float.BYTES); // 3 for position + 2 u v
    }

    private float[] createVertices() {
        return new float[] {
                0, 0, 0, 0, 0, //A
                1, 0, 0, 1, 0, //B
                0, 1, 0, 0, 1, //C

                0, 1, 0, 0, 1, //C
                1, 0, 0, 1, 0, //B
                1, 1, 0, 1, 1  //D
        };
    }
}
