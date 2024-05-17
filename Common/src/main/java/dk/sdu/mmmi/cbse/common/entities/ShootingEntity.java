package dk.sdu.mmmi.cbse.common.entities;

public class ShootingEntity extends Entity {
    private long lastShot = 0;

    public boolean shootCheck() {
        float shootDelay = 0.2f;
        if ((System.currentTimeMillis() - this.lastShot) / 1_000f > shootDelay) {
            this.lastShot = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
