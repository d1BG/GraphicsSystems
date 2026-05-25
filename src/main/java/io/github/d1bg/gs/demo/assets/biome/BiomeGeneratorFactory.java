package io.github.d1bg.gs.demo.assets.biome;

import static io.github.d1bg.gs.demo.assets.chunk.Chunk.noiseGen01;
import static io.github.d1bg.gs.demo.assets.chunk.Chunk.noiseGen03;


public class BiomeGeneratorFactory {
    private static final BiomeGenerator plainsGenerator = new PlainsGenerator();
    private static final BiomeGenerator desertGenerator = new DesertGenerator();
    private static final BiomeGenerator oceansGenerator = new OceansGenerator();
    private static final BiomeGenerator mountainsGenerator = new MountainsGenerator();

    public static Biomes getBiome(int x, int z) {
        double scale = 250;

        double rawNoise01 = noiseGen03.noise(x / scale, 0.0, z / scale);

        double normalizedNoise01 = (rawNoise01 + 1.0) / 2.0;

        Biomes b;
        if (normalizedNoise01 < 0.25) {
            b = Biomes.OCEANS;
        } else if (normalizedNoise01 < 0.5) {
            b = Biomes.DESERT;
        } else if (normalizedNoise01 < 0.75) {
            b = Biomes.PLAINS;
        } else {
            b = Biomes.MOUNTAINS;
        }

        normalizedNoise01 %= 0.25;
        b.setInfluence(1 - 8 * Math.abs(normalizedNoise01 - 0.125) );
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
