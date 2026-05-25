package io.github.d1bg.gs.demo.assets.biome;

import static io.github.d1bg.gs.demo.assets.chunk.Chunk.*;


public class BiomeGeneratorFactory {
    private static final BiomeGenerator plainsGenerator = new PlainsGenerator();
    private static final BiomeGenerator desertGenerator = new DesertGenerator();
    private static final BiomeGenerator oceansGenerator = new OceansGenerator();
    private static final BiomeGenerator mountainsGenerator = new MountainsGenerator();

    public static Biomes getBiome(int x, int z) {
        double scale = 250;

        double rawNoise01 = noiseGen01.noise(x / scale, 0.0, z / scale);
        double rawNoise02 = noiseGen02.noise(x / (scale*2), 0.0, z / (scale*2));
        double rawNoise03 = noiseGen03.noise(x / (scale/2), 0.0, z / (scale/2));

        double normalizedNoise01 = (rawNoise01 + 1.0) / 2.0;
        double normalizedNoise02 = (rawNoise02 + 1.0) / 2.0;
        double normalizedNoise03 = (rawNoise03 + 1.0) / 2.0;

        double finalNoise = normalizedNoise01*normalizedNoise02 + normalizedNoise03*normalizedNoise02;

        Biomes b;
        if (finalNoise < 0.25) {
            b = Biomes.OCEANS;
        } else if (finalNoise < 0.5) {
            b = Biomes.DESERT;
        } else if (finalNoise < 0.75) {
            b = Biomes.PLAINS;
        } else {
            b = Biomes.MOUNTAINS;
        }

        finalNoise %= 0.25;
        b.setInfluence(1 - 8 * Math.abs(finalNoise - 0.125) );
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
}
