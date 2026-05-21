package bg.tu_varna.sit.gs.core.input;

import java.util.HashMap;
import java.util.Map;
import static org.lwjgl.glfw.GLFW.*;

public class InputHandler {
    private static long window;
    private static final Map<Action, Integer> keyBindings = new HashMap<>();

    public void init(long activeWindow) {
        window = activeWindow;
        keyBindings.put(Action.DEMO_INCREASE_ALPHA, GLFW_KEY_UP);
        keyBindings.put(Action.DEMO_DECREASE_ALPHA, GLFW_KEY_DOWN);
    }

    public static boolean isActionPressed(Action action) {
        int key = keyBindings.get(action);
        return glfwGetKey(window, key) == GLFW_PRESS;
    }
}