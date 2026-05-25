package io.github.d1bg.gs.demo.assets.biome;

import io.github.d1bg.gs.demo.objects.cube.Block;

import static io.github.d1bg.gs.demo.assets.chunk.Chunk.*;

public class DesertGenerator implements BiomeGenerator {
    private double influence;

    public DesertGenerator() {}

    @Override
    public void setInfluence(double influence) {
        this.influence = influence;
    }

    @Override
    public int getHeight(int worldX, int worldZ) {
        double scale = DEFAULT_SCALE + (50 * this.influence);
        int maxTerrainHeight = Math.toIntExact(DEFAULT_TERRAIN_HEIGHT + Math.round(60 * this.influence));

        double rawNoise01 = noiseGen01.noise(worldX / scale, 0.0, worldZ / scale);
        double rawNoise02 = noiseGen02.noise(worldX / (scale/2), 0.0, worldZ / (scale/2));
        double rawNoise03 = noiseGen03.noise(worldX / (scale*3), 0.0, worldZ / (scale*3));

        double normalizedNoise01 = (rawNoise01 + 1.0) / 2.0;
        double normalizedNoise02 = (rawNoise02 + 1.0) / 2.0;
        double normalizedNoise03 = (rawNoise03 + 1.0) / 2.0;

        double newNoise = normalizedNoise01/3 + normalizedNoise02/3 + normalizedNoise03/3;

        return (int) (newNoise * maxTerrainHeight);
    }

    @Override
    public Block getBlock(int y, int finalHeight) {
        if (y == 0) {
            return Block.BEDROCK;
        } else if (y < finalHeight - 3) {
            return Block.STONE;
        } else if (y <= finalHeight) {
            return Block.SAND;
        } else if (y <= DEFAULT_WATER_LEVEL) {
            return Block.WATER;
        } else {
            return Block.AIR;
        }
    }
}
