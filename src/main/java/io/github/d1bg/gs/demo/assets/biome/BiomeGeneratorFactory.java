package io.github.d1bg.gs.demo.assets.biome;

import static io.github.d1bg.gs.demo.assets.chunk.Chunk.*;


public class BiomeGeneratorFactory {
    private static final BiomeGenerator plainsGenerator = new PlainsGenerator();
    private static final BiomeGenerator desertGenerator = new DesertGenerator();
    private static final BiomeGenerator oceansGenerator = new OceansGenerator();
    private static final BiomeGenerator mountainsGenerator = new MountainsGenerator();

    public static Biomes getBiome(int x, int z) {
        double scale = 500;

        double rawNoise01 = noiseGen01.noise(x / scale, 0.0, z / scale);
        rawNoise01 += noiseGen03.noise(x / (scale*2), 0.0, z / (scale*2));
        double finalNoise = (rawNoise01 + 2.0) / 4.0;

        Biomes b;
        if (finalNoise < 0.38) {
            b = Biomes.OCEANS;
            b.setInfluence(calculateInfluence(finalNoise, 0.0, 0.38));
        } else if (finalNoise < 0.50) {
            b = Biomes.DESERT;
            b.setInfluence(calculateInfluence(finalNoise, 0.38, 0.50));
        } else if (finalNoise < 0.62) {
            b = Biomes.PLAINS;
            b.setInfluence(calculateInfluence(finalNoise, 0.50, 0.62));
        } else {
            b = Biomes.MOUNTAINS;
            b.setInfluence(calculateInfluence(finalNoise, 0.62, 1.0));
        }
        return b;
    }

    public static BiomeGenerator getBiomeGenerator(Biomes biome) {
        return switch (biome) {
            case DESERT -> {
                desertGenerator.setInfluence(biome.getInfluence());
                yield desertGenerator;
            }
            case PLAINS -> {
                plainsGenerator.setInfluence(biome.getInfluence());
                yield plainsGenerator;
            }
            case MOUNTAINS -> {
                mountainsGenerator.setInfluence(biome.getInfluence());
                yield mountainsGenerator;
            }
            case OCEANS -> {
                oceansGenerator.setInfluence(biome.getInfluence());
                yield oceansGenerator;
            }
        };
    }

    public static double calculateInfluence(double noise, double min, double max) {
        double center = (min + max) / 2.0;
        double range = (max - min) / 2.0;

        return 1.0 - (Math.abs(noise - center) / range);
    }
}
