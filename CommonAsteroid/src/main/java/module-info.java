import dk.sdu.mmmi.cbse.common.asteroid.IAsteroidSplitterSPI;

module CommonAsteroid {
    uses IAsteroidSplitterSPI;
    exports dk.sdu.mmmi.cbse.common.asteroid;
    requires Common;
    requires java.net.http;
}
