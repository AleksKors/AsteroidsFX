package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ICollideableEntity;
import dk.sdu.mmmi.cbse.common.entities.ShootingEntity;
import dk.sdu.mmmi.cbse.common.entities.types.entityType;

public class Enemy extends ShootingEntity implements ICollideableEntity {
    private double radius;

    public Enemy() {
        this.setPolygonCoordinates(-8,-6,8,0,-8,6);
        this.setType(entityType.ENEMY);
        this.setRadius();
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    @Override
    public void setRadius() {
        double max = 0;
        double[] arr = this.getPolygonCoordinates();
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];
        this.radius = max;
    }

    @Override
    public void collide(World world, Entity otherEntity) {
        if (otherEntity.getType() == entityType.ASTEROID) {
            world.removeEntity(this);
        }
        if (otherEntity.getType() == entityType.BULLET) {
            Bullet bullet = (Bullet) otherEntity;
            if (bullet.getShooter() == this) {
                return;
            }
            world.removeEntity(this);
        }
    }
}
