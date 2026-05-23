package io.github.d1bg.gs.core.input;

import java.util.HashMap;
import java.util.Map;
import static org.lwjgl.glfw.GLFW.*;

public class InputHandler {
    private static long window;
    private static final Map<Action, Integer> keyBindings = new HashMap<>();

    private static double lastX = 0;
    private static double lastY = 0;
    private static double mouseXOffset = 0;
    private static double mouseYOffset = 0;
    private static boolean firstMouse = true;

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
        keyBindings.put(Action.LOCK_CURSOR, GLFW_KEY_R);


        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        glfwSetCursorPosCallback(window, (windowHandle, xpos, ypos) -> {
            if (firstMouse) {
                lastX = xpos;
                lastY = ypos;
                firstMouse = false;
            }

            mouseXOffset = xpos - lastX;
            mouseYOffset = lastY - ypos;

            lastX = xpos;
            lastY = ypos;
        });
    }

    public static boolean isActionPressed(Action action) {
        int key = keyBindings.get(action);
        return glfwGetKey(window, key) == GLFW_PRESS;
    }

    public static boolean isActionHeld(Action action) {
        int key = keyBindings.get(action);
        return glfwGetKey(window, key) != GLFW_RELEASE;
    }

    public static float getMouseDX() {
        float dx = (float) mouseXOffset;
        mouseXOffset = 0;
        return dx;
    }

    public static float getMouseDY() {
        float dy = (float) mouseYOffset;
        mouseYOffset = 0;
        return dy;
    }

    public static void lockedMouseToggle(){
        switch (glfwGetInputMode(window, GLFW_CURSOR)) {
            case GLFW_CURSOR_DISABLED:
                glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
                break;
            case GLFW_CURSOR_NORMAL:
                glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
                break;
        }
    }
}