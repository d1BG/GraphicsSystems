package io.github.d1bg.gs.demo.assets.chunk;

import io.github.d1bg.gs.demo.objects.cube.Block;
import io.github.d1bg.gs.utils.NoiseGenerator;

public class Chunk {
    static NoiseGenerator noiseGen01;

    public static final int WIDTH = 16;
    public static final int HEIGHT = 256;
    private Block[][][] blocks = new Block[WIDTH][HEIGHT][WIDTH];
    public final int chunkX, chunkY, chunkZ;

    public Chunk(int x, int y, int z) {
        this.chunkX = x;
        this.chunkY = y;
        this.chunkZ = z;

        if (noiseGen01 == null) {
            noiseGen01 = new NoiseGenerator();
        }

        generateTerrain();
    }

    private void generateTerrain() {
        double scale = 60.0;
        int maxTerrainHeight = 160;

        for (int x = 0; x < WIDTH; x++) {
            for (int z = 0; z < WIDTH; z++) {
                double worldX = (this.chunkX * WIDTH) + x;
                double worldZ = (this.chunkZ * WIDTH) + z;

                // -1.0 to 1.0
                double rawNoise = noiseGen01.noise(worldX / scale, 0.0, worldZ / scale);

                // 0.0 to 1.0
                double normalizedNoise = (rawNoise + 1.0) / 2.0;

                int height = (int) (normalizedNoise * maxTerrainHeight);

                for (int y = 0; y < HEIGHT; y++) {
                    if (y < height - 3) {
                        blocks[x][y][z] = Block.STONE;
                    } else if (y < height) {
                        blocks[x][y][z] = Block.DIRT;
                    } else if (y == height) {
                        blocks[x][y][z] = Block.GRASS;
                    } else if (y <= 64) {
                        blocks[x][y][z] = Block.WATER;
                    } else {
                        blocks[x][y][z] = Block.AIR;
                    }
                }
            }
        }
    }

    public Block getBlock(int x, int y, int z) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT || z < 0 || z >= WIDTH) {
            return Block.AIR;
        }
        return blocks[x][y][z];
    }
}