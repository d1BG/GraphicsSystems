package io.github.d1bg.gs.demo.objects.chunk;

import io.github.d1bg.gs.demo.assets.chunk.Chunk;
import io.github.d1bg.gs.demo.assets.chunk.ChunkGeometry;
import io.github.d1bg.gs.material.TextureArray;
import io.github.d1bg.gs.mesh.DrawMode;
import io.github.d1bg.gs.mesh.Geometry;
import io.github.d1bg.gs.mesh.Mesh;
import io.github.d1bg.gs.model.TexturedModelElement;
import io.github.d1bg.gs.scene.SceneObject;

public class ChunkObject extends SceneObject {
    private Mesh chunkMesh;
    private Chunk chunkData;

    private static TextureArray sharedChunkTextures;

    public ChunkObject(Chunk chunkData) {
        this.chunkData = chunkData;

        float[] vertices = ChunkMeshBuilder.buildMesh(chunkData);
        Geometry chunk = new ChunkGeometry(vertices);
        this.chunkMesh = new Mesh(DrawMode.TRIANGLES, chunk);

        if (sharedChunkTextures == null) {
            String[] textures = {
                    "textures/stone.png",
                    "textures/dirt.png",
                    "textures/grass_block_top.png",
                    "textures/water.png",
            };
            sharedChunkTextures = new TextureArray(textures);
        }

        getModel().addElement(new TexturedModelElement(chunkMesh, sharedChunkTextures));
    }

    @Override
    public void update() {
    }
}