package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ShootingEntity;
import dk.sdu.mmmi.cbse.common.entities.types.entityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerTest {
    private Player player;
    private World world;
    private GameData gameData;
    private GameKeys keys;
    private PlayerControlSystem playerControlSystem;
    private PlayerPlugin playerPlugin;


    @BeforeEach
    void setUp() {
        this.player = new Player();
        this.world = new World();
        this.world.addEntity(this.player);
        this.gameData = mock(GameData.class);
        this.keys = mock(GameKeys.class);
        this.playerControlSystem = new PlayerControlSystem();
        this.playerPlugin = new PlayerPlugin();
    }

    @Test
    void playerConstructorTest() {
        //Setup
        Player p = new Player();

        //Verification
        assertNotNull(p);
        assertInstanceOf(Player.class, p);
    }

    @Test
    public void playerCollideWithAsteroidTest() {
        //Setup
        Entity asteroid = mock(Entity.class);
        when(asteroid.getType()).thenReturn(entityType.ASTEROID);

        //Method call
        this.player.collide(this.world, asteroid);

        //Verification
        assertEquals(9, this.player.getHealth());
    }

    @Test
    public void playerCollideWithEnemyBulletTest() {
        //Setup
        Bullet bullet = mock(Bullet.class);

        ShootingEntity enemy = mock(ShootingEntity.class);
        when(bullet.getType()).thenReturn(entityType.BULLET);
        when(bullet.getShooter()).thenReturn(enemy);

        //Method call
        this.player.collide(this.world, bullet);

        //Verification
        assertEquals(9, this.player.getHealth());
    }

    @Test
    public void playerCollideWithOwnBulletTest() {
        //Setup
        Bullet bullet = mock(Bullet.class);
        when(bullet.getType()).thenReturn(entityType.BULLET);
        when(bullet.getShooter()).thenReturn(this.player);

        //Method call
        this.player.collide(this.world, bullet);

        //Verification
        assertEquals(10, this.player.getHealth());
    }

    @Test
    public void playerCollideAndDies() {
        //Setup
        this.player.setHealth(1);
        Entity asteroid = mock(Entity.class);
        when(asteroid.getType()).thenReturn(entityType.ASTEROID);
        assertFalse(this.world.getEntities().isEmpty());

        //Method call
        this.player.collide(this.world, asteroid);

        //Verification
        assertEquals(0, this.player.getHealth());
        assertTrue(this.world.getEntities().isEmpty());
    }

    @Test
    public void playerCollideWithNonCollideableEntity() {
        //Setup
        Entity entity = mock(Entity.class);

        //Method call
        player.collide(this.world, entity);

        //Verification
        assertEquals(10, this.player.getHealth());
    }

    @Test
    public void processPlayerLeftRotationTest() {
        //Setup
        when(this.gameData.getKeys()).thenReturn(this.keys);
        when(this.keys.isDown(GameKeys.LEFT)).thenReturn(true);

        //Method call
        this.playerControlSystem.process(this.gameData, this.world);

        //Verification
        assertEquals(-2, this.player.getRotation());
    }

    @Test
    public void processPlayerRightRotationTest() {
        //Setup
        when(gameData.getKeys()).thenReturn(this.keys);
        when(keys.isDown(GameKeys.LEFT)).thenReturn(false);
        when(keys.isDown(GameKeys.RIGHT)).thenReturn(true);

        //Method call
        this.playerControlSystem.process(this.gameData, this.world);

        //Verification
        assertEquals(2, this.player.getRotation());
    }

    @Test
    public void processPlayerForwardMovementTest() {
        //Setup
        when(this.gameData.getKeys()).thenReturn(this.keys);
        when(this.keys.isDown(GameKeys.LEFT)).thenReturn(false);
        when(this.keys.isDown(GameKeys.RIGHT)).thenReturn(false);
        when(this.keys.isDown(GameKeys.UP)).thenReturn(true);
        when(this.gameData.getDisplayWidth()).thenReturn(100);
        when(this.gameData.getDisplayHeight()).thenReturn(100);

        //Method call
        this.playerControlSystem.process(this.gameData, this.world);

        //Verification
        assertEquals(0, this.player.getY());
        assertEquals(1, this.player.getX());
    }

    @Test
    public void processPlayerShootingTest() {
        //Setup
        when(this.gameData.getKeys()).thenReturn(this.keys);
        when(this.keys.isDown(GameKeys.LEFT)).thenReturn(false);
        when(this.keys.isDown(GameKeys.RIGHT)).thenReturn(false);
        when(this.keys.isDown(GameKeys.UP)).thenReturn(false);
        when(this.keys.isDown(GameKeys.SPACE)).thenReturn(true);
        when(this.gameData.getDisplayWidth()).thenReturn(100);
        when(this.gameData.getDisplayHeight()).thenReturn(100);

        BulletSPI bulletSPI = mock(BulletSPI.class);
        when(bulletSPI.createBullet(any(ShootingEntity.class), any(GameData.class))).thenReturn(new Bullet(this.player));
        PlayerControlSystem controlSystem = spy(new PlayerControlSystem());
        doReturn(List.of(bulletSPI)).when(controlSystem).getBulletSPIs();


        //Method call
        controlSystem.process(this.gameData, this.world);

        //Verification
        verify(bulletSPI, times(1)).createBullet(any(ShootingEntity.class), any(GameData.class));
        assertEquals(1, this.world.getEntities(Bullet.class).size());
    }

    @Test
    public void processPlayerOutOfBoundsTest() {
        //Setup
        when(this.gameData.getKeys()).thenReturn(this.keys);
        when(this.gameData.getDisplayWidth()).thenReturn(100);
        when(this.gameData.getDisplayHeight()).thenReturn(100);
        this.player.setX(-1);
        this.player.setY(-1);
        assertTrue(this.player.outOfBounds(this.gameData));

        //Method call
        this.playerControlSystem.process(this.gameData, this.world);

        //Verification
        assertEquals(0, this.player.getX());
        assertEquals(0, this.player.getY());
    }

    @Test
    void createPlayerShipTest() {
        //Setup
        this.world.getEntities().clear();
        when(gameData.getDisplayHeight()).thenReturn(100);
        when(gameData.getDisplayWidth()).thenReturn(100);

        //Method call
        Entity playerShip = this.playerPlugin.createPlayerShip(this.gameData);

        //Verification
        assertInstanceOf(Player.class, playerShip);
        assertEquals(50, playerShip.getX());
        assertEquals(50, playerShip.getY());
    }

    @Test
    void startTest() {
        //Setup
        this.world.getEntities().clear();

        //Method call
        this.playerPlugin.start(this.gameData, this.world);

        //Verification
        assertEquals(1, this.world.getEntities().size());
    }

    @Test
    void stopTest() {
        assertFalse(this.world.getEntities().isEmpty());

        //Method call
        this.playerPlugin.stop(this.gameData, this.world);

        //Verification
        assertTrue(this.world.getEntities().isEmpty());
    }
}