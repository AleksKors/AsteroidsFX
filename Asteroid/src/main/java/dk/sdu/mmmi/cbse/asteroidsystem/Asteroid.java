package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author Aleksander
 */
public class Asteroid extends Entity {
    public Asteroid() {
        //this.setRadius(9);
        this.setPolygonCoordinates(-9,0,-3,9,3,6,6,9,9,0,3,-6,-6,-3);
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
}
