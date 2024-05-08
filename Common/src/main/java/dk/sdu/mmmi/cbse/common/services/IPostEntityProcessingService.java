package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {

        /**
     * This method is responsible for processing game entities after all other entity processing has been completed.
     * It is called every frame and is used to finalize the state of the game world for the current frame.
     *
     * @param gameData An instance of GameData that holds data related to the game state.
     * @param world An instance of World that represents the game world.
     */
    void process(GameData gameData, World world);
}
