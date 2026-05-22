package io.github.d1bg.gs.core.input;

import java.util.HashMap;
import java.util.Map;
import static org.lwjgl.glfw.GLFW.*;

public class InputHandler {
    private static long window;
    private static final Map<Action, Integer> keyBindings = new HashMap<>();

    public void init(long activeWindow) {
        window = activeWindow;
        keyBindings.put(Action.DEMO_INCREASE_ALPHA, GLFW_KEY_RIGHT_BRACKET);
        keyBindings.put(Action.DEMO_DECREASE_ALPHA, GLFW_KEY_LEFT_BRACKET);

        keyBindings.put(Action.CAMERA_MOVE_FORWARD, GLFW_KEY_W);
        keyBindings.put(Action.CAMERA_MOVE_BACKWARD, GLFW_KEY_S);
        keyBindings.put(Action.CAMERA_MOVE_LEFT, GLFW_KEY_A);
        keyBindings.put(Action.CAMERA_MOVE_RIGHT, GLFW_KEY_D);

        keyBindings.put(Action.CAMERA_ROTATE_UP, GLFW_KEY_UP);
        keyBindings.put(Action.CAMERA_ROTATE_DOWN, GLFW_KEY_DOWN);
        keyBindings.put(Action.CAMERA_ROTATE_LEFT, GLFW_KEY_LEFT);
        keyBindings.put(Action.CAMERA_ROTATE_RIGHT, GLFW_KEY_RIGHT);
    }

    public static boolean isActionPressed(Action action) {
        int key = keyBindings.get(action);
        return glfwGetKey(window, key) == GLFW_PRESS;
    }
}