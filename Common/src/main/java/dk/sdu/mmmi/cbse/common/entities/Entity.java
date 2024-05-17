package dk.sdu.mmmi.cbse.common.entities;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.entities.types.entityType;

import java.io.Serializable;
import java.util.UUID;

/**
 * This class represents a generic entity in the game.
 * Each entity has a unique ID, a set of polygon coordinates, a position (x, y), a rotation, a type, and health.
 */
public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();

    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private entityType type;
    private int health;

    /**
     * Returns the unique ID of the entity.
     *
     * @return The unique ID of the entity.
     */
    public String getID() {
        return ID.toString();
    }

    /**
     * Sets the health of the entity.
     *
     * @param health The health to set.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Returns the health of the entity.
     *
     * @return The health of the entity.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the polygon coordinates of the entity.
     *
     * @param coordinates The polygon coordinates to set.
     */
    public void setPolygonCoordinates(double... coordinates) {
        this.polygonCoordinates = coordinates;
    }

    /**
     * Returns the polygon coordinates of the entity.
     *
     * @return The polygon coordinates of the entity.
     */
    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    /**
     * Sets the x-coordinate of the entity's position.
     *
     * @param x The x-coordinate to set.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returns the x-coordinate of the entity's position.
     *
     * @return The x-coordinate of the entity's position.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the y-coordinate of the entity's position.
     *
     * @param y The y-coordinate to set.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns the y-coordinate of the entity's position.
     *
     * @return The y-coordinate of the entity's position.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the rotation of the entity.
     *
     * @param rotation The rotation to set.
     */
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    /**
     * Returns the rotation of the entity.
     *
     * @return The rotation of the entity.
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Sets the type of the entity.
     *
     * @param type The type to set.
     * @see entityType
     */
    public void setType(entityType type) {
        this.type = type;
    }

    /**
     * Returns the type of the entity.
     *
     * @return The type of the entity.
     * @see entityType
     */
    public entityType getType() {
        return type;
    }

    /**
     * Checks if the entity is out of the game bounds.
     *
     * @param gameData The game data containing the display width and height.
     * @return True if the entity is out of bounds, false otherwise.
     */
    public boolean outOfBounds(GameData gameData) {
        if (this.x < 0) return true;
        if (this.y < 0) return true;
        if (this.x > gameData.getDisplayWidth()) return true;
        return this.y > gameData.getDisplayHeight();
    }
}
