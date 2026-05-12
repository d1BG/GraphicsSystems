package bg.tu_varna.sit.gs.mesh;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Mesh {

    private final int vao;
    private final VertexBuffer vbo;
    private final int vertexCount;
    private final DrawMode drawMode;

    public Mesh(DrawMode drawMode, Geometry geometry) {
        this.drawMode = drawMode;
        this.vao = createVAO();
        bindVAO();
        this.vbo = createVBO(geometry);
        configureAttributes(geometry);
        this.vertexCount = geometry.getVertexCount();
        unbindVAO();
    }

    private int createVAO() {
        return glGenVertexArrays();
    }

    private void bindVAO() {
        glBindVertexArray(vao);
    }

    private void unbindVAO() {
        glBindVertexArray(0);
    }

    private VertexBuffer createVBO(Geometry geometry) {
        VertexBuffer buffer = new VertexBuffer(geometry.getData());
        buffer.bind();
        return buffer;
    }

    private void configureAttributes(Geometry geometry) {
        int stride = geometry.getStride();
        int offset = 0;

        for (VertexAttribute attr : geometry.getAttributes()) {
            setupAttribute(attr, stride, offset);
            offset += attr.getComponentSize();
        }
    }

    private void setupAttribute(VertexAttribute attr, int stride, int offset) {

        glVertexAttribPointer(
                attr.getLocation(),
                attr.getComponentSize(),
                GL_FLOAT,
                false,
                stride,
                (long) offset * Float.BYTES
        );

        glEnableVertexAttribArray(attr.getLocation());
    }

    public void render() {
        bindVAO();
        glDrawArrays(drawMode.getGlMode(), 0, vertexCount);
        unbindVAO();
    }
}