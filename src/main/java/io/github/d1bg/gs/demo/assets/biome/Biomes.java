package io.github.d1bg.gs.demo.assets.biome;

public enum Biomes {
    PLAINS,
    MOUNTAINS,
    OCEANS,
    DESERT;

    double influence;

    public double getInfluence() {
        return influence;
    }

    public void setInfluence(double influence) {
        this.influence = influence;
    }
}
