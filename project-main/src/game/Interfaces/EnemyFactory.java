package game.Interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import game.Grounds.EastSpawn;
import game.Grounds.SpawningGround;
import game.Grounds.WestSpawn;

/**
 * Interface to implement EnemyFactory for east and west spawning ground
 * @author Tan Yin Cheng
 * Modified by: Muhammad Ibrahim bin Mohd Yusni
 * @see EastSpawn
 * @see WestSpawn
 */
public interface EnemyFactory {
    /**
     * A factory for creating respective enemy according to provided spawningGround.
     * @param ground type of spawningGround
     * @return new instance of enemy
     */
    Actor createActor(SpawningGround ground);

    /**
     * A method to obtain the side of the map the class represents
     * @return side of map (East/West)
     */
    String getSideOfMap();
}