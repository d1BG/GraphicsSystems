package bg.tu_varna.sit.gs.core;


public abstract class Engine {
    private Window window;
    private boolean running = false;

    public void setWindow(Window window) {
        this.window = window;
    }

    public Window getWindow() {
        return window;
    }

    public final void run() {
        init();
        loop();
        cleanup();
    }

    protected abstract void init();

    protected abstract void update();

    protected abstract void render();

    protected abstract void cleanup();

    private void loop() {
        running = true;

        while (running && !window.shouldClose()) {
            update();
            render();

            window.update();
        }
    }

    public void stop() {
        running = false;
    }
}
