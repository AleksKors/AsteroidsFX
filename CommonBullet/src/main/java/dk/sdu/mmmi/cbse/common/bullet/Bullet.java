package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ICollideableEntity;
import dk.sdu.mmmi.cbse.common.entities.ShootingEntity;

/**
 * @author corfixen
 */
public class Bullet extends Entity implements ICollideableEntity {
    private double radius;
    private ShootingEntity shooter;

    public Bullet(ShootingEntity shooter) {
        this.shooter = shooter;

    }

    public ShootingEntity getShooter() {
        return shooter;
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
        if (otherEntity instanceof ShootingEntity shootingEntity) {
            if (shootingEntity == this.shooter) {
                return;
            }
        }
        world.removeEntity(this);
    }
}
