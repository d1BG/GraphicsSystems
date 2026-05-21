package io.github.d1bg.gs.core;

import io.github.d1bg.gs.core.input.InputHandler;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;

public class Window {
    private final long handle;
    private final int width;
    private final int height;
    private final String title;
    private InputHandler inputHandler;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;

        initGLFW();
        this.handle = createWindow();
        initContext();
        configureWindow();

        inputHandler = new InputHandler();
        inputHandler.init(handle);
    }


    public void update() {
        glfwSwapBuffers(handle);
        glfwPollEvents();
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(handle);
    }

    public long getHandle() {
        return handle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void destroy() {
        glfwDestroyWindow(handle);
        glfwTerminate();
    }

    private void initGLFW() {
        if (!glfwInit()) {
            throw new IllegalStateException("GLFW initialization failed");
        }
    }

    private long createWindow() {
        long windowHandle = glfwCreateWindow(width, height, title, 0, 0);
        if (windowHandle == 0) {
            throw new RuntimeException("Window creation failed");
        }
        return windowHandle;
    }

    private void initContext() {
        glfwMakeContextCurrent(handle);
        createCapabilities();
    }

    private void configureWindow() {
        glfwSwapInterval(1);
    }
}