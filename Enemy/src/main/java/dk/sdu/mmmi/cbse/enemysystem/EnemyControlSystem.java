package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ShootingEntity;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemyControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        if (world.getEntities(Enemy.class).size()<2){
            Enemy enemy = new Enemy();
            int x = (int) (Math.random() * gameData.getDisplayWidth());
            int y = (int) (Math.random() * gameData.getDisplayHeight());
            enemy.setX(x);
            enemy.setY(y);
            world.addEntity(enemy);
        }
        for (Entity enemy : world.getEntities(Enemy.class)) {
            int shoot = (int) (Math.random()*50);
            int rotation = (int) (Math.random()*20-10);
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);
            if (rotation <= 5 & rotation >= -5) {
                enemy.setRotation(enemy.getRotation() + rotation);
            }

            if (shoot == 1) {
                if (enemy instanceof ShootingEntity shootingEnemy) {
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {
                                world.addEntity(spi.createBullet(shootingEnemy, gameData));
                            }
                    );
                }
            }

            if (enemy.outOfBounds(gameData)){
                if (enemy.getX() < 0) {
                    enemy.setX(0);
                    enemy.setRotation(enemy.getRotation() + 180);
                }
                if (enemy.getX() > gameData.getDisplayWidth()) {
                    enemy.setX(gameData.getDisplayWidth());
                    enemy.setRotation(enemy.getRotation() + 180);
                }
                if (enemy.getY() < 0) {
                    enemy.setY(0);
                    enemy.setRotation(enemy.getRotation() + 180);
                }
                if (enemy.getY() > gameData.getDisplayHeight()) {
                    enemy.setY(gameData.getDisplayHeight());
                    enemy.setRotation(enemy.getRotation() + 180);
                }
            }
            
                                        
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
