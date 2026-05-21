package io.github.d1bg.gs.shaders;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

    private final int programId;

    public ShaderProgram(String vertexSource, String fragmentSource) {

        int vertexId = compileShader(vertexSource, GL_VERTEX_SHADER);
        int fragmentId = compileShader(fragmentSource, GL_FRAGMENT_SHADER);

        programId = glCreateProgram();
        glAttachShader(programId, vertexId);
        glAttachShader(programId, fragmentId);
        glLinkProgram(programId);

        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            throw new RuntimeException("Shader linking failed:\n" +
                    glGetProgramInfoLog(programId));
        }

        glDeleteShader(vertexId);
        glDeleteShader(fragmentId);
    }

    private int compileShader(String source, int type) {
        int id = glCreateShader(type);
        glShaderSource(id, source);
        glCompileShader(id);

        if (glGetShaderi(id, GL_COMPILE_STATUS) == 0) {
            throw new RuntimeException("Shader compile failed:\n" +
                    glGetShaderInfoLog(id));
        }
        return id;
    }

    public void use() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void cleanup() {
        glDeleteProgram(programId);
    }

    public void setUniform(String name, Matrix4f matrix) {
        int location = glGetUniformLocation(programId, name);
        if (location != -1) {
            FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
            matrix.get(buffer);
            glUniformMatrix4fv(location, false, buffer);
        }
    }

    public void setUniform(String name, float[] values) {
        int location = glGetUniformLocation(programId, name);
        if (location != -1) {
            glUniform1fv(location, values);
        }
    }

    public void setUniform(String name, int value) {
        int location = glGetUniformLocation(programId, name);
        if (location != -1) {
            glUniform1i(location, value);
        }
    }

    public void setUniform(String name, float value) {
        int location = glGetUniformLocation(programId, name);
        if (location != -1) {
            glUniform1f(location, value);
        }
    }

    public void setUniform(String name, Vector3f value) {
        int location = glGetUniformLocation(programId, name);
        if (location != -1) {
            glUniform3f(location, value.x, value.y, value.z);
        }
    }
}
