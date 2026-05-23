package io.github.d1bg.gs.demo.assets.chunk;

import io.github.d1bg.gs.mesh.Geometry;
import io.github.d1bg.gs.mesh.VertexAttribute;

public class ChunkGeometry extends Geometry {

    public ChunkGeometry(float[] vertices) {
        setData(vertices);

        setAttributes(new VertexAttribute[]{
                new VertexAttribute(0, 3),
                new VertexAttribute(1, 3),
        });

        setStride(4 * Float.BYTES);
    }
}