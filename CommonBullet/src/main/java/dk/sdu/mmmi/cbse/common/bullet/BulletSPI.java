package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ShootingEntity;

/**
 *
 * @author corfixen
 */
public interface BulletSPI {
    Entity createBullet(ShootingEntity e, GameData gameData);
}
