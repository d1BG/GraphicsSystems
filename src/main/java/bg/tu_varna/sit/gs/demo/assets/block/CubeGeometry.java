package bg.tu_varna.sit.gs.demo.assets.block;

import bg.tu_varna.sit.gs.mesh.Geometry;
import bg.tu_varna.sit.gs.mesh.VertexAttribute;

public class CubeGeometry extends Geometry {
    public CubeGeometry() {
        setData(createVertices());
        setAttributes(new VertexAttribute[]{
                new VertexAttribute(0, 3),
                new VertexAttribute(1, 3),
                new VertexAttribute(2, 3)

        });
        setStride(6 * Float.BYTES); // 3 for position + 3 for color
    }

    private float[] createVertices() {
        return new float[]{
                //front
                -0.5f, -0.5f, -0.5f,    1, 0, 0, //A
                 0.5f, -0.5f, -0.5f,    1, 0, 0, //B
                -0.5f,  0.5f, -0.5f,    1, 0, 0, //C

                -0.5f, 0.5f, -0.5f,     1, 0, 0, //C
                0.5f, -0.5f, -0.5f,     1, 0, 0, //B
                0.5f,  0.5f, -0.5f,     1, 0, 0, //D

                //left
                -0.5f,  0.5f, -0.5f,    0, 0, 1, //C
                -0.5f, -0.5f, -0.5f,    0, 0, 1, //A
                -0.5f, -0.5f,  0.5f,    0, 0, 1, //F

                -0.5f, 0.5f, -0.5f,     0, 0, 1, //C
                -0.5f, -0.5f, 0.5f,     0, 0, 1, //F
                -0.5f, 0.5f,  0.5f,     0, 0, 1, //E

                //right
                0.5f, -0.5f, -0.5f,     0, 1, 0, //B
                0.5f,  0.5f, -0.5f,     0, 1, 0, //D
                0.5f, -0.5f,  0.5f,     0, 1, 0, //G

                0.5f, -0.5f, 0.5f,      0, 1, 0, //G
                0.5f, 0.5f, -0.5f,      0, 1, 0, //D
                0.5f, 0.5f,  0.5f,      0, 1, 0, //H

                //back
                 0.5f,  0.5f, 0.5f,     1, 0, 1, //H
                 0.5f, -0.5f, 0.5f,     1, 0, 1, //G
                -0.5f, -0.5f, 0.5f,     1, 0, 1, //F

                -0.5f, -0.5f, 0.5f,     1, 0, 1, //F
                 0.5f,  0.5f, 0.5f,     1, 0, 1, //H
                -0.5f,  0.5f, 0.5f,     1, 0, 1, //E

                //bottom :3
                -0.5f, -0.5f, -0.5f,    1, 1, 0, //A
                -0.5f, -0.5f,  0.5f,    1, 1, 0, //F
                 0.5f, -0.5f,  0.5f,    1, 1, 0, //G

                -0.5f, -0.5f, -0.5f,    1, 1, 0, //A
                 0.5f, -0.5f,  0.5f,    1, 1, 0, //G
                 0.5f, -0.5f, -0.5f,    1, 1, 0, //B

                //top >:3
                -0.5f, 0.5f,  0.5f,     0, 1, 1, //E
                 0.5f, 0.5f,  0.5f,     0, 1, 1, //H
                -0.5f, 0.5f, -0.5f,     0, 1, 1, //C

                 0.5f, 0.5f,  0.5f,     0, 1, 1, //H
                -0.5f, 0.5f, -0.5f,     0, 1, 1, //C
                 0.5f, 0.5f, -0.5f,     0, 1, 1  //D
        };
    }
}
