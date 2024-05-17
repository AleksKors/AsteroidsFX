package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.IAsteroidSplitterSPI;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;

public class AsteroidSplitterImplementation implements IAsteroidSplitterSPI {
    @Override
    public void splitAsteroid(Entity asteroid, World world) {
        if (asteroid instanceof Asteroid asteroid1 && asteroid1.getHealth() > 1) {
            Asteroid asteroid2 = AsteroidControlSystem.createAsteroid(asteroid1.getHealth() - 1);
            Asteroid asteroid3 = AsteroidControlSystem.createAsteroid(asteroid1.getHealth() - 1);
            Asteroid[] asteroidList = {asteroid2, asteroid3};
            double rotation = Math.random() * 360;
            for (int i = 0; i < asteroidList.length; i++) {
                Asteroid ast = asteroidList[i];
                if (i == 0) {
                    ast.setRotation(rotation);
                }
                if (i == 1) {
                    ast.setRotation(rotation + 180);
                }
                double changeX = Math.cos(Math.toRadians(ast.getRotation())) * ast.getRadius() * 3;
                double changeY = Math.sin(Math.toRadians(ast.getRotation())) * ast.getRadius() * 3;
                ast.setX(asteroid.getX() + changeX);
                ast.setY(asteroid.getY() + changeY);
                world.addEntity(ast);
            }
            world.removeEntity(asteroid);
        }
    }
}
