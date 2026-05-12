package bg.tu_varna.sit.gs.material;

import bg.tu_varna.sit.gs.utils.ResourceLoader;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30C.glGenerateMipmap;
public class Texture {
    private final int textureID;
    private final String resourcePath;
    public Texture(String resourcePath) {
        textureID = glGenTextures();
        this.resourcePath = resourcePath;
        init();
    }
    private void init() {
        glBindTexture(GL_TEXTURE_2D, textureID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        STBImage.stbi_set_flip_vertically_on_load(true);
        ResourceLoader loader = new ResourceLoader();
        ByteBuffer imageBuffer = loader.loadResourceToBuffer(resourcePath);
        ByteBuffer image = STBImage.stbi_load_from_memory(imageBuffer, width, height, channels,
                4);
        if (image == null) {
            throw new RuntimeException("Failed to load texture: "
                    + STBImage.stbi_failure_reason());
        }
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA,
                width.get(0), height.get(0),
                0, GL_RGBA, GL_UNSIGNED_BYTE, image);
        glGenerateMipmap(GL_TEXTURE_2D);
        STBImage.stbi_image_free(image);
    }
    public void bind(int unit) {
        glActiveTexture(GL_TEXTURE0 + unit);
        glBindTexture(GL_TEXTURE_2D, textureID);
    }
    public void cleanup() {
        glDeleteTextures(textureID);
    }
}
