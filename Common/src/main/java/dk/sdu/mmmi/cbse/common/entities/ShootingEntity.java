package dk.sdu.mmmi.cbse.common.entities;

public abstract class ShootingEntity extends Entity{
    private final float SHOOT_DELAY = 0.2f;
    private double lastShot;

    public float getSHOOT_DELAY() {
        return SHOOT_DELAY;
    }

    public double getLastShot() {
        return this.lastShot;
    }
    public void setLastShot() {
        this.lastShot = System.nanoTime();
    }
}
