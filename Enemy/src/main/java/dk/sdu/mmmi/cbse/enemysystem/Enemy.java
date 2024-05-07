package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.entities.ShootingEntity;

public class Enemy extends ShootingEntity {
    public Enemy() {
        this.setPolygonCoordinates(-5,-5,10,0,-5,5);
    }
}
