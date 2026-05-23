package io.github.d1bg.gs.demo.assets.chunk;

import io.github.d1bg.gs.demo.objects.cube.Block;

import java.util.Random;

public class Chunk {
    public static final int SIZE = 16;
    private Block[][][] blocks = new Block[SIZE][SIZE][SIZE];
    public final int chunkX, chunkY, chunkZ;

    public Chunk(int x, int y, int z) {
        this.chunkX = x;
        this.chunkY = y;
        this.chunkZ = z;

        generateTerrain();
    }

    private void generateTerrain() {
        Random random = new Random();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    blocks[i][j][k] = Block.AIR;
                }
            }
        }

        for (int i = 0; i < SIZE; i++) { // Y
            for  (int j = 0; j < SIZE; j++) { // X
                for (int k = 0; k < SIZE; k++) { // Z
                    if (random.nextBoolean()) {
                        if (i > SIZE - 5 && i != SIZE - 1) {
                            blocks[j][i][k] = Block.DIRT;
                        } else if (i == SIZE - 1) {
                            blocks[j][i][k] = Block.GRASS;
                        } else {
                            blocks[j][i][k] = Block.STONE;
                        }
                    }
                }
            }
        }
    }

    public Block getBlock(int x, int y, int z) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE || z < 0 || z >= SIZE) {
            return Block.AIR;
        }
        return blocks[x][y][z];
    }
}