package game.Grounds;

import edu.monash.fit2099.engine.actors.Actor;
import game.Actors.Enemies.GiantCrab;
import game.Actors.Enemies.HeavySkeletalSwordsman;
import game.Actors.Enemies.LoneWolf;
import game.Interfaces.EnemyFactory;

/**
 * A class representing the West Spawn to spawn enemies on the west side.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by: Muhammad Ibrahim bin Mohd Yusni
 */
public class WestSpawn implements EnemyFactory {

    @Override
    public Actor createActor(SpawningGround ground) {
        if (ground.getDisplayChar() == 'n') {
            return new HeavySkeletalSwordsman();
        } else if (ground.getDisplayChar() == '&') {
            return new LoneWolf();
        } else if (ground.getDisplayChar() == '~') {
            return new GiantCrab();
        }
        return null;
    }

    @Override
    public String getSideOfMap() {
        return "West";
    }
}
