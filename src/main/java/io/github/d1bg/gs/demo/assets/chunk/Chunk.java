package io.github.d1bg.gs.demo.assets.chunk;

import io.github.d1bg.gs.demo.assets.biome.BiomeGenerator;
import io.github.d1bg.gs.demo.assets.biome.BiomeGeneratorFactory;
import io.github.d1bg.gs.demo.objects.cube.Block;
import io.github.d1bg.gs.utils.NoiseGenerator;

import static io.github.d1bg.gs.demo.assets.biome.BiomeGeneratorFactory.getBiomeGenerator;

public class Chunk {
    public static NoiseGenerator noiseGen01;
    public static NoiseGenerator noiseGen02;
    public static NoiseGenerator noiseGen03;

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
        for (int x = 0; x < WIDTH; x++) {
            for (int z = 0; z < WIDTH; z++) {
                int worldX = (chunkX * WIDTH) + x;
                int worldZ = (chunkZ * WIDTH) + z;

                BiomeGenerator biomeGen = getBiomeGenerator(BiomeGeneratorFactory.getBiome(worldX, worldZ));
                int height = biomeGen.getHeight(worldX, worldZ);

                for (int y = 0; y < HEIGHT; y++) {
                    blocks[x][y][z] = biomeGen.getBlock(y, height);
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

                    if (normalizedNoise01 > 0.5 && y < height && (blocks[x][y][z] != Block.WATER && blocks[x][y][z] != Block.BEDROCK)) {
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