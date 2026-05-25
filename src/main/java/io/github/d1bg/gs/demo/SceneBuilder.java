package io.github.d1bg.gs.demo;

import io.github.d1bg.gs.demo.assets.chunk.Chunk;
import io.github.d1bg.gs.demo.objects.chunk.ChunkMap;
import io.github.d1bg.gs.demo.objects.chunk.ChunkObject;
import io.github.d1bg.gs.demo.objects.cube.Block;
import io.github.d1bg.gs.light.DirectionalLight;
import io.github.d1bg.gs.scene.Scene;
import org.joml.Vector3f;

public class SceneBuilder {
    public static Scene scene = new Scene();
    public static ChunkMap chunkMap = new ChunkMap();

    public static Scene build() {
        setDirLight();

        for (int i = -10; i <= 10; i++) {
            for (int j = -10; j <= 10; j++) {
                buildChunk(i*16, j*16);
            }
        }

        return scene;
    }

    private static void setDirLight() {
        DirectionalLight directionalLight = new DirectionalLight(
                new Vector3f(5f, -10f, 0f),
                new Vector3f(0.50f, 0.50f, 0.50f),
                new Vector3f(0.5f, 0.5f, 0.5f),
                new Vector3f(1.0f, 1.0f, 1.0f)
        );
        scene.setDirectionalLight(directionalLight);
    }

    public static void buildChunk(int x, int z) {
        ChunkMap.ChunkPos chunkPosition = new ChunkMap.ChunkPos(
                (int) Math.ceil(x/16.0) - 1,
                (int) Math.ceil(z/16.0) - 1
        );

        if (!chunkMap.checkChunk(chunkPosition)) {
            chunkMap.addChunk(chunkPosition);
            
            Chunk chunkData = new Chunk(chunkPosition.x, 0, chunkPosition.z);

            ChunkObject chunkMeshObject = new ChunkObject(chunkData);
            scene.addSceneObject(chunkMeshObject);
        }
    }
}
