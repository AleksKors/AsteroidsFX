
import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidSplitterImplementation;
import dk.sdu.mmmi.cbse.common.asteroid.IAsteroidSplitterSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonAsteroid;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroidsystem.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroidsystem.AsteroidControlSystem;
    provides IAsteroidSplitterSPI with AsteroidSplitterImplementation;
    
}
