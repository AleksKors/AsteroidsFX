package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ICollideableEntity;
import dk.sdu.mmmi.cbse.common.entities.ShootingEntity;
import dk.sdu.mmmi.cbse.common.entities.types.entityType;

/**
 *
 * @author corfixen
 */
public class Bullet extends Entity implements ICollideableEntity {
    private ShootingEntity shooter;
    public Bullet() {
        this.setRadius(1);
        this.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        this.setType(entityType.BULLET);
    }

    @Override
    public void collide(World world, Entity otherEntity) {
        if (otherEntity instanceof ShootingEntity shootingEntity) {
            if (shootingEntity == this.shooter) {return;}
        }
        world.removeEntity(this);
    }

    public ShootingEntity getShooter() {
        return shooter;
    }

    public void setShooter(ShootingEntity shooter) {
        this.shooter = shooter;
    }
}
