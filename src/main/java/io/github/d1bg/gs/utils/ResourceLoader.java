package io.github.d1bg.gs.utils;

import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ResourceLoader {
    public String loadResource(String path) {
        try (var is = getClass().getClassLoader().getResourceAsStream(path)) {
            assert is != null;
            return new String(is.readAllBytes());
        } catch (Exception e) {
            throw new RuntimeException("Cannot load resource: " + path);
        }
    }

    public ByteBuffer loadResourceToBuffer(String resourcePath) {
        try (InputStream stream = getClass()
                .getClassLoader()
                .getResourceAsStream(resourcePath)) {
            if (stream == null) {
                throw new RuntimeException("Resource not found: " + resourcePath);
            }
            byte[] bytes = stream.readAllBytes();
            ByteBuffer buffer = BufferUtils.createByteBuffer(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource: " + resourcePath, e);
        }
    }
}
