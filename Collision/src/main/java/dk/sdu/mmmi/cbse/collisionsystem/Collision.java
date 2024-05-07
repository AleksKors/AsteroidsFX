package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.entities.ICollideableEntity;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class Collision implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            if (entity1 instanceof ICollideableEntity collideableEntity1) {
                for (Entity entity2 : world.getEntities()) {
                    // if the two entities are identical, skip the iteration
                    if (entity1.getID().equals(entity2.getID())) {
                        continue;
                    }
                    if (entity2 instanceof ICollideableEntity collideableEntity2) {
                        // CollisionDetection
                        if (this.collides(entity1, entity2)) {
                            collideableEntity1.collide(world, entity2);
                            collideableEntity2.collide(world, entity1);
                        }
                    }
                }
            }
        }

    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

}