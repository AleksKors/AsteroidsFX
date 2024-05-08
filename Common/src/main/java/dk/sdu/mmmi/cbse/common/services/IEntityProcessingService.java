package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * This method is responsible for processing game entities.
     * It is called every frame and is used to update the state of the game world.
     *
     * @param gameData An instance of GameData that holds data related to the game state.
     * @param world An instance of World that represents the game world.
     */
    void process(GameData gameData, World world);
}
