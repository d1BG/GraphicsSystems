package bg.tu_varna.sit.gs.camera;

public class PerspectiveCamera extends Camera {

    private final float fov, near, far, aspectRatio;

    public PerspectiveCamera(float fov, float near, float far, int width, int height) {
        this.fov = fov;
        this.near = near;
        this.far = far;

        this.aspectRatio = (float) width / height;

        updateProjection();
    }

    @Override
    public void updateProjection() {
        getProjectionMatrix().identity().perspective(fov, aspectRatio, near, far);
    }
}
