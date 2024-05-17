package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ShootingEntity;
import dk.sdu.mmmi.cbse.common.entities.types.entityType;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()))*3;
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()))*3;
            bullet.setX(bullet.getX() + changeX);
            bullet.setY(bullet.getY() + changeY);

            if (bullet.outOfBounds(gameData)) {
                world.removeEntity(bullet);
            }
        }
    }

    @Override
    public Entity createBullet(ShootingEntity shooter, GameData gameData) {
        if (shooter.shootCheck()) {
            Bullet bullet = new Bullet(shooter);
            bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
            bullet.setRadius();
            bullet.setType(entityType.BULLET);
            bullet.setX(shooter.getX());
            bullet.setY(shooter.getY());
            bullet.setRotation(shooter.getRotation());
            return bullet;
        }
        return null;
    }
}
