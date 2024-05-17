package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ICollideableEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jcs
 */
public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    public List<Entity> entitiesToRemove = new ArrayList<>();
    private int asteroidsDestroyed = 0;

    public String addEntity(Entity entity) {
        if (entity != null) {
            entityMap.put(entity.getID(), entity);
            return entity.getID();
        } else return null;
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
        entitiesToRemove.add(entity);
    }

    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

    public void updateAsteroidsDestroyed() {
        asteroidsDestroyed++;
    }

    public int getAsteroidsDestroyed() {
        return asteroidsDestroyed;
    }

}
