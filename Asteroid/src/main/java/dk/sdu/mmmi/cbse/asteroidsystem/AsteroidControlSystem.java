package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.types.entityType;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        if (world.getEntities(Asteroid.class).size()<2){
            this.spawn(gameData, world);
        }
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
            asteroid.setX(asteroid.getX() + changeX);
            asteroid.setY(asteroid.getY() + changeY);
            if (asteroid.outOfBounds(gameData)) {
                world.removeEntity(asteroid);
            }
        }
    }

    public static Asteroid createAsteroid() {
        Asteroid asteroid = new Asteroid();
        asteroid.setHealth(3);
        setPolygonCoordinates(asteroid);
        asteroid.setRadius();
        return asteroid;
    }

    public static Asteroid createAsteroid(int health) {
        Asteroid asteroid = new Asteroid();
        asteroid.setHealth(health);
        setPolygonCoordinates(asteroid);
        asteroid.setRadius();
        return asteroid;
    }

    private static void setPolygonCoordinates(Asteroid asteroid) {
        int scale = asteroid.getHealth();
        asteroid.setPolygonCoordinates(
                -10 * scale, 5 * scale,
                -4 * scale, 10 * scale,
                4 * scale, 6 * scale,
                6 * scale, 10 * scale,
                10 * scale, 5 * scale,
                4 * scale, -6 * scale,
                -6 * scale, -4 * scale);
        asteroid.setType(entityType.ASTEROID);
    }

    public static void spawn(GameData gameData, World world) {
        Asteroid asteroid = createAsteroid();
        world.addEntity(asteroid);
        int spawnSide = (int) (Math.random() * 4);
        int x = (int) (Math.random() * gameData.getDisplayWidth());
        int y = (int) (Math.random() * gameData.getDisplayHeight());
        if (spawnSide == 0) {
            asteroid.setX(0);
            asteroid.setY(y);
            asteroid.setRotation(Math.random() * 180 - 90);
        } else if (spawnSide == 1) {
            asteroid.setX(gameData.getDisplayWidth());
            asteroid.setY(y);
            asteroid.setRotation(Math.random() * 180 + 90);
        } else if (spawnSide == 2) {
            asteroid.setX(x);
            asteroid.setY(0);
            asteroid.setRotation(Math.random() * 180);
        } else if (spawnSide == 3) {
            asteroid.setX(x);
            asteroid.setY(gameData.getDisplayHeight());
            asteroid.setRotation(Math.random() * -180);
        }
    }
}
