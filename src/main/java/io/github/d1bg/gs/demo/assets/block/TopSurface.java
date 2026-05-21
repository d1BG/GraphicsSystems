package io.github.d1bg.gs.demo.assets.block;

import io.github.d1bg.gs.mesh.Geometry;
import io.github.d1bg.gs.mesh.VertexAttribute;

public class TopSurface extends Geometry {
    public TopSurface() {
        setData(createVertices());
        setAttributes(new VertexAttribute[]{
                new VertexAttribute(0, 3),
                new VertexAttribute(1, 2)
        });
        setStride(5 * Float.BYTES); // 3 for position + 2 u v
    }

    private float[] createVertices() {
        return new float[] {
                0, 1, 1, 0, 1, //E
                1, 1, 1, 1, 1, //H
                0, 1, 0, 0, 0, //C

                1, 1, 1, 1, 1, //H
                0, 1, 0, 0, 0, //C
                1, 1, 0, 1, 0  //D
        };
    }
}
