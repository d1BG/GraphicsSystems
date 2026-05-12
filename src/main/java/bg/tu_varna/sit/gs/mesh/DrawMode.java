package bg.tu_varna.sit.gs.mesh;

import static org.lwjgl.opengl.GL11.*;

public enum DrawMode {

    TRIANGLES(GL_TRIANGLES),
    LINES(GL_LINES),
    POINTS(GL_POINTS),
    LINE_STRIP(GL_LINE_STRIP),
    LINE_LOOP(GL_LINE_LOOP),
    TRIANGLE_STRIP(GL_TRIANGLE_STRIP),
    TRIANGLE_FAN(GL_TRIANGLE_FAN);

    private final int glMode;

    DrawMode(int glMode) {
        this.glMode = glMode;
    }

    public int getGlMode() {
        return glMode;
    }
}