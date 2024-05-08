package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {

    /**
     * This method is invoked during the execution of the start() method in the main class.
     * It is responsible for initializing game components and setting up the game environment.
     *
     * @param gameData An instance of GameData that holds data related to the game state.
     * @param world An instance of World that represents the game world.
     */
    void start(GameData gameData, World world);

    /**
     * This method is called when the game is stopped.
     * It is responsible for cleaning up resources and resetting the game state.
     *
     * @param gameData An instance of GameData that holds data related to the game state.
     * @param world An instance of World that represents the game world.
     */
    void stop(GameData gameData, World world);
}
