package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;

public interface IAsteroidSplitterSPI {
    /**
     * This method is responsible for splitting an asteroid entity into smaller parts.
     *
     * @param asteroid The asteroid entity that is to be split.
     * @param world An instance of World that represents the game world.
     */
    void splitAsteroid(Entity asteroid, World world);
}
