package io.github.d1bg.gs.material;

import io.github.d1bg.gs.utils.ResourceLoader;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL12C.glTexSubImage3D;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30C.GL_TEXTURE_2D_ARRAY;
import static org.lwjgl.opengl.GL30C.glGenerateMipmap;
import static org.lwjgl.opengl.GL42C.glTexStorage3D;

public class TextureArray {
    private final int textureID;
    private final String[] resourcePaths;

    public TextureArray(String[] resourcePaths) {
        if (resourcePaths == null || resourcePaths.length == 0) {
            throw new IllegalArgumentException("Resource paths array cannot be null or empty.");
        }
        textureID = glGenTextures();
        this.resourcePaths = resourcePaths;
        init();
    }

    private void init() {
        glBindTexture(GL_TEXTURE_2D_ARRAY, textureID);

        glTexParameteri(GL_TEXTURE_2D_ARRAY, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D_ARRAY, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D_ARRAY, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D_ARRAY, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        STBImage.stbi_set_flip_vertically_on_load(true);
        ResourceLoader loader = new ResourceLoader();

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);

        // 1. Load the first image to establish the dimensions for the whole array
        ByteBuffer firstImageBuffer = loader.loadResourceToBuffer(resourcePaths[0]);
        ByteBuffer firstImage = STBImage.stbi_load_from_memory(firstImageBuffer, width, height, channels, 4);

        if (firstImage == null) {
            throw new RuntimeException("Failed to load texture array base image: " + STBImage.stbi_failure_reason());
        }

        int w = width.get(0);
        int h = height.get(0);
        int layerCount = resourcePaths.length;

        // 2. Allocate immutable storage for the 3D texture array (1 mipmap level, RGBA8 format)
        glTexStorage3D(GL_TEXTURE_2D_ARRAY, 1, GL_RGBA8, w, h, layerCount);

        // 3. Upload the first layer (Z-offset = 0)
        glTexSubImage3D(GL_TEXTURE_2D_ARRAY, 0, 0, 0, 0, w, h, 1, GL_RGBA, GL_UNSIGNED_BYTE, firstImage);
        STBImage.stbi_image_free(firstImage);

        // 4. Load and upload the remaining layers
        for (int i = 1; i < layerCount; i++) {
            ByteBuffer imgBuf = loader.loadResourceToBuffer(resourcePaths[i]);
            ByteBuffer img = STBImage.stbi_load_from_memory(imgBuf, width, height, channels, 4);

            if (img == null) {
                throw new RuntimeException("Failed to load texture layer " + i + ": " + STBImage.stbi_failure_reason());
            }

            // Upload at Z-offset `i`
            glTexSubImage3D(GL_TEXTURE_2D_ARRAY, 0, 0, 0, i, w, h, 1, GL_RGBA, GL_UNSIGNED_BYTE, img);
            STBImage.stbi_image_free(img);
        }

        glGenerateMipmap(GL_TEXTURE_2D_ARRAY);
    }

    public void bind(int unit) {
        glActiveTexture(GL_TEXTURE0 + unit);
        glBindTexture(GL_TEXTURE_2D_ARRAY, textureID);
    }

    public void cleanup() {
        glDeleteTextures(textureID);
    }
}