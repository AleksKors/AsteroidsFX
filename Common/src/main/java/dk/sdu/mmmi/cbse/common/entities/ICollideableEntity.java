package dk.sdu.mmmi.cbse.common.entities;

import dk.sdu.mmmi.cbse.common.data.World;

/**
 * This interface represents an entity that can collide with other entities.
 * Implementing classes should provide a way to get and set the radius of the entity,
 * which is used for collision detection, and a method to handle the collision.
 */
public interface ICollideableEntity {
    /**
     * Gets the radius of the entity. The radius is used for collision detection.
     *
     * @return the radius of the entity.
     */
    double getRadius();

    /**
     * Sets the radius of the entity. The radius is used for collision detection.
     */
    void setRadius();

    /**
     * Handles the collision between this entity and another entity.
     *
     * @param world       The world in which the collision occurred.
     * @param otherEntity The other entity involved in the collision.
     */
    void collide(World world, Entity otherEntity);
}
