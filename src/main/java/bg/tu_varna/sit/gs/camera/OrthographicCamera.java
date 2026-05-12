package bg.tu_varna.sit.gs.camera;

public class OrthographicCamera extends Camera {
    private final float left, right, bottom, top, near, far;

    public OrthographicCamera(float left, float right, float bottom, float top, float near, float far) {
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
        this.near = near;
        this.far = far;

        updateProjection();
    }

    @Override
    public void updateProjection() {
        getProjectionMatrix().identity().ortho(left, right, bottom, top, near, far);
    }
}
