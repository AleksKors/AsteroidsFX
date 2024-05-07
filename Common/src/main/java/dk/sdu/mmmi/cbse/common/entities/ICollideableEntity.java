package dk.sdu.mmmi.cbse.common.entities;

import dk.sdu.mmmi.cbse.common.data.World;

public interface ICollideableEntity {
    void collide(World world, Entity otherEntity);
}
