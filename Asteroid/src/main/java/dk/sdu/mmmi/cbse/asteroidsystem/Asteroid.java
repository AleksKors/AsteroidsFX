package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ICollideableEntity;
import dk.sdu.mmmi.cbse.common.entities.types.entityType;

/**
 *
 * @author Aleksander
 */
public class Asteroid extends Entity implements ICollideableEntity {
    public Asteroid() {
        this.setRadius(9);
        this.setPolygonCoordinates(-9,0,-3,9,3,6,6,9,9,0,3,-6,-6,-3);
        this.setType(entityType.ASTEROID);
    }

    public static void spawn(GameData gameData, World world) {
        Asteroid asteroid = new Asteroid();
        world.addEntity(asteroid);
        int spawnSide = (int)(Math.random() * 4);
        int x = (int)(Math.random()*gameData.getDisplayWidth());
        int y = (int)(Math.random()*gameData.getDisplayHeight());
        if (spawnSide == 0) {
            asteroid.setX(0);
            asteroid.setY(y);
            asteroid.setRotation(Math.random()*180-90);
        }
        else if (spawnSide == 1) {
            asteroid.setX(gameData.getDisplayWidth());
            asteroid.setY(y);
            asteroid.setRotation(Math.random()*180+90);
        }
        else if (spawnSide == 2) {
            asteroid.setX(x);
            asteroid.setY(0);
            asteroid.setRotation(Math.random()*180);
        }
        else if (spawnSide == 3) {
            asteroid.setX(x);
            asteroid.setY(gameData.getDisplayHeight());
            asteroid.setRotation(Math.random()*-180);
        }
    }

    @Override
    public void collide(World world, Entity otherEntity) {
        world.removeEntity(this);
    }
}
