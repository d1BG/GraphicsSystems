package io.github.d1bg.gs.demo.assets.biome;

import io.github.d1bg.gs.demo.objects.cube.Block;

public interface BiomeGenerator {
    static int DEFAULT_SCALE = 75;
    static int DEFAULT_TERRAIN_HEIGHT = 150;
    static int DEFAULT_WATER_LEVEL = 70;

    int getHeight(int worldX, int worldZ);
    Block getBlock(int y, int finalHeight);

    void setInfluence(double influence);
}
