package io.github.d1bg.gs.mesh;


import static org.lwjgl.opengl.GL15.*;

public class VertexBuffer {
    private final int id;

    public VertexBuffer(float[] data) {
        id = glGenBuffers();
        bind();
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        unbind();
    }

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void dispose() {
        glDeleteBuffers(id);
    }

    public int getId() {
        return id;
    }

    public void cleanup() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(id);
    }
}