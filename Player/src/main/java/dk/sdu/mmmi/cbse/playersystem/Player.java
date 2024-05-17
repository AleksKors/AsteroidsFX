package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ICollideableEntity;
import dk.sdu.mmmi.cbse.common.entities.ShootingEntity;
import dk.sdu.mmmi.cbse.common.entities.types.entityType;

public class Player extends ShootingEntity implements ICollideableEntity {
    private double radius;
    public Player() {
        this.setHealth(10);
        this.setPolygonCoordinates(-8,-6,8,0,-8,6);
        this.setRadius();
        this.setType(entityType.PLAYER);
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
        int oldHealth = getHealth();
        if (otherEntity.getType() == entityType.ASTEROID) {
            this.setHealth(oldHealth -1);
        }
        if (otherEntity.getType() == entityType.BULLET) {
            Bullet bullet = (Bullet) otherEntity;
            if (bullet.getShooter() != this) {
                this.setHealth(oldHealth -1);
            }
        }
        if (getHealth() <= 0) {world.removeEntity(this);}
    }
}
