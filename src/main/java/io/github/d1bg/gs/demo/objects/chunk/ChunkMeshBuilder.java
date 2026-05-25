package io.github.d1bg.gs.demo.objects.chunk;

import io.github.d1bg.gs.demo.assets.chunk.Chunk;
import io.github.d1bg.gs.demo.objects.cube.Block;

import java.util.ArrayList;
import java.util.List;

public class ChunkMeshBuilder {
    private static final float[] TOP_FACE = {
            0, 1, 1,
            1, 1, 1,
            0, 1, 0,

            1, 1, 1,
            0, 1, 0,
            1, 1, 0
    };

    private static final float[] BOTTOM_FACE = {
            0, 0, 1,
            1, 0, 1,
            0, 0, 0,

            1, 0, 1,
            0, 0, 0,
            1, 0, 0
    };

    private static final float[] FRONT_FACE = {
            0, 0, 1,
            1, 0, 1,
            0, 1, 1,

            0, 1, 1,
            1, 0, 1,
            1, 1, 1
    };

    private static final float[] BACK_FACE = {
            0, 0, 0,
            1, 0, 0,
            0, 1, 0,

            0, 1, 0,
            1, 0, 0,
            1, 1, 0
    };

    private static final float[] LEFT_FACE = {
            0, 1, 0,
            0, 0, 0,
            0, 0, 1,

            0, 1, 0,
            0, 0, 1,
            0, 1, 1
    };

    private static final float[] RIGHT_FACE = {
            1, 1, 0,
            1, 0, 0,
            1, 0, 1,

            1, 1, 0,
            1, 0, 1,
            1, 1, 1
    };

    public static float[] buildMesh(Chunk chunk) {
        List<Float> vertices = new ArrayList<>();

        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int y = 0; y < Chunk.HEIGHT; y++) {
                for (int z = 0; z < Chunk.WIDTH; z++) {

                    Block currentBlock = chunk.getBlock(x, y, z);
                    if (currentBlock == Block.AIR) continue;

                    float offsetX = (chunk.chunkX * Chunk.WIDTH) + x;
                    float offsetY = (chunk.chunkY * Chunk.HEIGHT) + y;
                    float offsetZ = (chunk.chunkZ * Chunk.WIDTH) + z;

                    switch (chunk.getBlock(x, y + 1, z)) {
                        case Block.WATER:
                            if (currentBlock == Block.WATER) break;
                        case Block.AIR:
                            addFace(vertices, TOP_FACE, offsetX, offsetY, offsetZ, getBlockId(currentBlock));
                    }

                    switch (chunk.getBlock(x, y - 1, z)) {
                        case Block.WATER:
                            if (currentBlock == Block.WATER) break;
                        case Block.AIR:
                        addFace(vertices, BOTTOM_FACE, offsetX, offsetY, offsetZ, getBlockId(currentBlock));
                    }

                    switch (chunk.getBlock(x, y, z + 1)) {
                        case Block.WATER:
                            if (currentBlock == Block.WATER) break;
                        case Block.AIR:
                        addFace(vertices, FRONT_FACE, offsetX, offsetY, offsetZ, getBlockId(currentBlock));
                    }

                    switch (chunk.getBlock(x, y, z - 1)) {
                        case Block.WATER:
                            if (currentBlock == Block.WATER) break;
                        case Block.AIR:
                        addFace(vertices, BACK_FACE, offsetX, offsetY, offsetZ, getBlockId(currentBlock));
                    }

                    switch (chunk.getBlock(x + 1, y, z)) {
                        case Block.WATER:
                            if (currentBlock == Block.WATER) break;
                        case Block.AIR:
                        addFace(vertices, RIGHT_FACE, offsetX, offsetY, offsetZ, getBlockId(currentBlock));
                    }

                    switch (chunk.getBlock(x - 1, y, z)) {
                        case Block.WATER:
                            if (currentBlock == Block.WATER) break;
                        case Block.AIR:
                        addFace(vertices, LEFT_FACE, offsetX, offsetY, offsetZ, getBlockId(currentBlock));
                    }
                }
            }
        }

        float[] finalMesh = new float[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            finalMesh[i] = vertices.get(i);
        }
        return finalMesh;
    }

    private static float getBlockId(Block block) {
        if (block == null) {
            return 0.0f;
        }

        return switch (block) {
            case STONE -> 0.0f;
            case DIRT -> 1.0f;
            case GRASS -> 2.0f;
            case SAND -> 3.0f;
            case WATER -> 4.0f;
            case BEDROCK -> 5.0f;
            default -> 0;
        };
    }

    private static void addFace(List<Float> vertices, float[] faceData, float dx, float dy, float dz, float blockId) {
        for (int i = 0; i < faceData.length; i += 3) {
            vertices.add(faceData[i] + dx);
            vertices.add(faceData[i + 1] + dy);
            vertices.add(faceData[i + 2] + dz);
            vertices.add(blockId);
        }
    }
}
