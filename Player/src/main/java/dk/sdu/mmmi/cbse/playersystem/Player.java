package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ICollideableEntity;
import dk.sdu.mmmi.cbse.common.entities.ShootingEntity;
import dk.sdu.mmmi.cbse.common.entities.types.entityType;

/**
 *
 * @author Emil
 */
public class Player extends ShootingEntity implements ICollideableEntity {
    public Player() {
        this.setPolygonCoordinates(-8,-6,8,0,-8,6);
        this.setRadius(8);
        this.setType(entityType.PLAYER);
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
