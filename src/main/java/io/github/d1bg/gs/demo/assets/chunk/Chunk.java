package io.github.d1bg.gs.demo.assets.chunk;

import io.github.d1bg.gs.demo.objects.cube.Block;
import io.github.d1bg.gs.utils.NoiseGenerator;

public class Chunk {
    static NoiseGenerator noiseGen01;
    static NoiseGenerator noiseGen02;
    static NoiseGenerator noiseGen03;

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
            noiseGen02 = new NoiseGenerator();
            noiseGen03 = new NoiseGenerator();
        }

        generateTerrain();
        generateCaves();
    }

    private void generateTerrain() {
        double scale = 60.0;
        int maxTerrainHeight = 200;

        for (int x = 0; x < WIDTH; x++) {
            for (int z = 0; z < WIDTH; z++) {
                double worldX = (this.chunkX * WIDTH) + x;
                double worldZ = (this.chunkZ * WIDTH) + z;

                // -1.0 to 1.0
                double rawNoise01 = noiseGen01.noise(worldX / scale, 0.0, worldZ / scale);
                double rawNoise02 = noiseGen02.noise(worldX / (scale/2), 0.0, worldZ / (scale/2));
                double rawNoise03 = noiseGen03.noise(worldX / (scale*3), 0.0, worldZ / (scale*3));

                // 0.0 to 1.0
                double normalizedNoise01 = (rawNoise01 + 1.0) / 2.0;
                double normalizedNoise02 = (rawNoise02 + 1.0) / 2.0;
                double normalizedNoise03 = (rawNoise03 + 1.0) / 2.0;

                double newNoise = normalizedNoise01/3 + normalizedNoise02/3 + normalizedNoise03/3;

                int height = (int) (newNoise * maxTerrainHeight);

                for (int y = 0; y < HEIGHT; y++) {
                    if (y == 0) {
                        blocks[x][y][z] = Block.BEDROCK;
                    } else if (y < height - 3) {
                        blocks[x][y][z] = Block.STONE;
                    } else if (y < height) {
                        blocks[x][y][z] = Block.DIRT;
                    } else if (y == height) {
                        blocks[x][y][z] = Block.GRASS;
                    } else if (y <= 80) {
                        blocks[x][y][z] = Block.WATER;
                    } else {
                        blocks[x][y][z] = Block.AIR;
                    }
                }
            }
        }
    }

    private void generateCaves() {
        double scale = 10.0;
        int maxTerrainHeight = 120;

        for (int x = 0; x < WIDTH; x++) {
            for (int z = 0; z < WIDTH; z++) {
                for (int y = 0; y < HEIGHT; y++) {
                    double worldX = (this.chunkX * WIDTH) + x;
                    double worldZ = (this.chunkZ * WIDTH) + z;

                    // -1.0 to 1.0
                    double rawNoise01 = noiseGen01.noise(worldX / scale, y / scale, worldZ / scale);

                    // 0.0 to 1.0
                    double normalizedNoise01 = (rawNoise01 + 1.0) / 2.0;
                    normalizedNoise01 *= 0.8;

                    int height = (int) (normalizedNoise01 * maxTerrainHeight) + 15;

                    if (normalizedNoise01 > 0.5 && y<height && (blocks[x][y][z] != Block.WATER && blocks[x][y][z] != Block.BEDROCK)) {
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