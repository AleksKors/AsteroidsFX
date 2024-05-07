package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;

public interface IAsteroidSplitter {
    void splitAsteroid(Entity asteroid, World world);
}
