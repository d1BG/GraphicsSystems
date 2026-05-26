package io.github.d1bg.gs.core;

import imgui.ImGui;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.type.ImInt;

public class LwjglImGuiLayer {
    private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

    public static String position = "";
    public static String biome = "";
    public static ImInt renderDistance = new ImInt(10);

    public void initImGui(long windowHandle) {
        ImGui.createContext();

        imGuiGlfw.init(windowHandle, true);
        imGuiGl3.init("#version 460 core");
    }

    public void renderImGui() {
        imGuiGl3.newFrame();
        imGuiGlfw.newFrame();
        ImGui.newFrame();

        ImGui.begin("Game Info");
        ImGui.inputInt(" Render Distance", renderDistance);
        ImGui.text(position);
        ImGui.text(biome);
        ImGui.end();

        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());
    }

    public void destroyImGui() {
        imGuiGl3.shutdown();
        imGuiGlfw.shutdown();
        ImGui.destroyContext();
    }
}