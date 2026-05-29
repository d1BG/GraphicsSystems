package io.github.d1bg.gs.demo.objects.chunk;

import io.github.d1bg.gs.demo.assets.chunk.Chunk;
import org.joml.Vector2i;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChunkMap {
    private Map<ChunkPos, Chunk> chunkData = new HashMap<ChunkPos, Chunk>();

    public void addChunk(ChunkPos chunkPos, Chunk chunk) {
        chunkData.put(chunkPos, chunk);
    }

    public Chunk checkChunk(ChunkPos pos) {
        return chunkData.getOrDefault(pos, null);
    }

    public Chunk checkChunk(Vector3f CamPos) {
        ChunkPos pos = new ChunkPos((int) Math.ceil(CamPos.x/16), (int) Math.ceil(CamPos.z/16));
        return chunkData.getOrDefault(pos, null);
    }

    public static class ChunkPos {
        public int x;
        public int z;

        public ChunkPos(int x, int z) {
            this.x = x;
            this.z = z;
        }

        public Vector2i getPos() {
            return new Vector2i(x, z);
        }

        public ChunkPos getChunkPos(){
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            ChunkPos chunkPos = (ChunkPos) o;
            return x == chunkPos.x && z == chunkPos.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, z);
        }
    }
}
