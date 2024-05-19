package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.entities.Entity;
import dk.sdu.mmmi.cbse.common.entities.ICollideableEntity;
import dk.sdu.mmmi.cbse.common.entities.types.entityType;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

/**
 *
 * @author Aleksander
 */
public class Asteroid extends Entity implements ICollideableEntity {
    private double radius;
    private Collection<? extends IAsteroidSplitterSPI> getAsteroidSplitterSPIs() {
        return ServiceLoader.load(IAsteroidSplitterSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public Asteroid() {}

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
        if (otherEntity.getType() == entityType.BULLET && this.getHealth() >= 2) {
            getAsteroidSplitterSPIs().stream().findFirst().ifPresent(
                    spi ->{
                        spi.splitAsteroid(this, world);
                    }
            );
            return;
        }
        if (otherEntity.getType() == entityType.BULLET && this.getHealth() <= 1) {
            world.removeEntity(this);
            updateScore(1);
        }
        world.removeEntity(this);
    }

    public void updateScore(int point) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/score?point=" + point))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
    }
}
