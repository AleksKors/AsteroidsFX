package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
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
    private IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImplementation();

    public Asteroid() {
        this.setHealth(3);
        this.setRadius(10 * this.getHealth());
        this.setPolygonCoordinates(
                -10 * this.getHealth(), 5 * this.getHealth(),
                -4 * this.getHealth(), 10 * this.getHealth(),
                4 * this.getHealth(), 6 * this.getHealth(),
                6 * this.getHealth(), 10 * this.getHealth(),
                10 * this.getHealth(), 5 * this.getHealth(),
                4 * this.getHealth(), -6 * this.getHealth(),
                -6 * this.getHealth(), -4 * this.getHealth());
        this.setType(entityType.ASTEROID);
    }
    public Asteroid(int health) {
        this.setHealth(health);
        this.setRadius(10 * health);
        this.setPolygonCoordinates(
                -10 * health, 5 * health,
                -4 * health, 10 * health,
                4 * health, 6 * health,
                6 * health, 10 * health,
                10 * health, 5 * health,
                4 * health, -6 * health,
                -6 * health, -4 * health);
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
        if (otherEntity instanceof Bullet && this.getHealth() > 1) {
            this.asteroidSplitter.splitAsteroid(this, world);
            world.removeEntity(this);
            return;
        }
        world.removeEntity(this);
    }
}
