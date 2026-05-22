package io.github.d1bg.gs.demo.assets.fullBlock;

import io.github.d1bg.gs.mesh.Geometry;
import io.github.d1bg.gs.mesh.VertexAttribute;
import org.joml.Vector3f;

public class BackSurface extends Geometry {
    private final Vector3f position;

    public BackSurface(Vector3f position) {
        this.position = position;
        setData(createVertices());
        setAttributes(new VertexAttribute[]{
                new VertexAttribute(0, 3),
                new VertexAttribute(1, 2)
        });
        setStride(5 * Float.BYTES); // 3 for position + 2 u v
    }

    private float[] createVertices() {
        float[] vertices = new float[] {
                0, 0, 1, 0, 0, //F
                1, 0, 1, 1, 0, //G
                0, 1, 1, 0, 1, //E

                0, 1, 1, 0, 1, //E
                1, 0, 1, 1, 0, //G
                1, 1, 1, 1, 1  //H
        };

        for (int i = 0; i < vertices.length; i+=5){
            vertices[i] += position.x;
            vertices[i+1] += position.y;
            vertices[i+2] += position.z;
        }

        return vertices;
    }
}
