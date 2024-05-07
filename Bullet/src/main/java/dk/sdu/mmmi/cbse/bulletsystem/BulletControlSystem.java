package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ShootingEntity;
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
        if (shootCheck(shooter)) {
            Bullet bullet = new Bullet();
            bullet.setX(shooter.getX());
            bullet.setY(shooter.getY());
            bullet.setRotation(shooter.getRotation());
            bullet.setShooter((ShootingEntity) shooter);
            ((ShootingEntity) shooter).setLastShot();
            return bullet;
        }
        return null;
    }

    private void setShape(Entity entity) {
    }

    public boolean shootCheck(Entity shooter) {
        if (shooter instanceof ShootingEntity) {
            return (System.nanoTime() - ((ShootingEntity) shooter).getLastShot()) / 1_000_000_000.0f > ((ShootingEntity) shooter).getSHOOT_DELAY();
        }
        return false;
    }

}
