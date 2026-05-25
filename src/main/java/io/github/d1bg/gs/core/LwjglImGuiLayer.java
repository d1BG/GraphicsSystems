package io.github.d1bg.gs.core;

import imgui.ImGui;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

public class LwjglImGuiLayer {
    // These instances handle the translation between ImGui and LWJGL
    private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

    public static String position = "";
    public static String biome = "";

    public void initImGui(long windowHandle) {
        ImGui.createContext();

        // Initialize GLFW binding (the 'true' flag installs standard input callbacks)
        imGuiGlfw.init(windowHandle, true);

        // Initialize OpenGL 3 binding (adjust the GLSL version string to match your setup)
        imGuiGl3.init("#version 460 core");
    }

    public void renderImGui() {
        // 1. Tell BOTH backends to prepare for a new frame
        imGuiGl3.newFrame();
        imGuiGlfw.newFrame();
        ImGui.newFrame();

        // 2. Build your ImGui UI
        ImGui.begin("Game Info");
        ImGui.text(position);
        ImGui.text(biome);
        ImGui.end();

        // 3. Render the UI
        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());
    }

    public void destroyImGui() {
        // Clean up resources in reverse order on application exit
        imGuiGl3.shutdown();
        imGuiGlfw.shutdown();
        ImGui.destroyContext();
    }
}